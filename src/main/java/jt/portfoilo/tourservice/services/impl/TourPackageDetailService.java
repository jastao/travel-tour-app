package jt.portfoilo.tourservice.services.impl;

import jt.portfoilo.tourservice.domain.TourPackageDetail;
import jt.portfoilo.tourservice.web.rest.mapper.TourPackageDetailMapper;
import jt.portfoilo.tourservice.web.rest.model.TourPackageDetailDTO;
import jt.portfoilo.tourservice.domain.TourPackageInfo;
import jt.portfoilo.tourservice.repositories.TourPackageDetailRepository;
import jt.portfoilo.tourservice.repositories.TourPackageInfoRepository;
import jt.portfoilo.tourservice.services.ITourPackageDetailService;
import jt.portfoilo.tourservice.web.rest.exceptions.TourPackageNotFoundException;
import jt.portfoilo.tourservice.web.rest.model.TourPackageDetailPagedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by JT on 3/24/2019
 */
@Service
@Transactional
public class TourPackageDetailService implements ITourPackageDetailService {
	
	private final Logger logger = LoggerFactory.getLogger(TourPackageDetailService.class);
	
	private final TourPackageDetailMapper tourPackageDetailMapper;
	private final TourPackageInfoRepository tourPackageRepository;
	private final TourPackageDetailRepository tourPackageDetailRepository;
	
	public TourPackageDetailService(TourPackageDetailMapper tourPackageDetailMapper,
									TourPackageInfoRepository tourPackageRepository,
									TourPackageDetailRepository tourPackageDetailRepository) {
		this.tourPackageDetailMapper = tourPackageDetailMapper;
		this.tourPackageRepository = tourPackageRepository;
		this.tourPackageDetailRepository = tourPackageDetailRepository;
	}
	
	@Override
	public TourPackageDetailDTO findDtoById(UUID tourPackageDetailId){

		logger.debug("Request to find TourPackageDetailDTO: {}", tourPackageDetailId);
		return tourPackageDetailMapper.tourPackageDetailToDto(findById(tourPackageDetailId));
	}

	@Override
	public TourPackageDetail findById(UUID tourPackageDetailId) {

		logger.debug("Request to find TourPackageDetail: {}", tourPackageDetailId);
		TourPackageDetail tourPackageDetail =
				tourPackageDetailRepository.findById(tourPackageDetailId)
					.orElseThrow( () -> new TourPackageNotFoundException("No tour package with id: " + tourPackageDetailId) );
		return tourPackageDetail;
	}

	@Override
	public TourPackageDetailDTO save(TourPackageDetailDTO tourPackageDetailDTO) {
		
		logger.debug("Request to save TourPackageDetail : {} ", tourPackageDetailDTO);
		TourPackageDetail tourPackageDetail = tourPackageDetailMapper.dtoToTourPackageDetail(tourPackageDetailDTO);
		tourPackageDetail = tourPackageDetailRepository.save(tourPackageDetail);
		return tourPackageDetailMapper.tourPackageDetailToDto(tourPackageDetail);
	}
	
	@Override
	public TourPackageDetailDTO delete(UUID tourPackageDetailId) {
		
		logger.debug("Request to delete TourPackageDetail : {}", tourPackageDetailId);
		
		TourPackageDetail deleted
				= tourPackageDetailRepository.findById(tourPackageDetailId)
					.orElseThrow( () -> new TourPackageNotFoundException("No tour package with id: " + tourPackageDetailId));
		
		tourPackageDetailRepository.delete(deleted);
		
		return tourPackageDetailMapper.tourPackageDetailToDto(deleted);
	}
	
	@Override
	@Transactional(readOnly = true)
	public TourPackageDetailDTO tourPackageByTourCode(String tourCode) {
		
		logger.debug("Request to retrieve tour package.");
		TourPackageInfo tourPackageInfo = this.tourPackageRepository.findByTourCode(tourCode)
				.orElseThrow(() -> new TourPackageNotFoundException("Tour Code not found."));
		
		TourPackageDetail tourPackageDetail
				= tourPackageDetailRepository.findByTourPackageInfo(tourPackageInfo);
		
		return this.tourPackageDetailMapper.tourPackageDetailToDto(tourPackageDetail);
	}
	
	@Override
	@Transactional(readOnly = true)
	public TourPackageDetailPagedList tourPackages(String depCity, Pageable request) {
		
		TourPackageDetailPagedList tourPackageDetailPageList;
		Page<TourPackageDetail> tourPackageDetailPage;
		
		if (!StringUtils.isEmpty(depCity) || depCity != null) {
			tourPackageDetailPage = tourPackageDetailRepository.findByDepartureCity(depCity, request);
		} else {
			tourPackageDetailPage = tourPackageDetailRepository.findAll(request);
		}
		
		tourPackageDetailPageList = new TourPackageDetailPagedList(
				tourPackageDetailPage.getContent()
						.stream()
						.map(tourPackageDetailMapper::tourPackageDetailToDto)
						.collect(Collectors.toList()),
				PageRequest.of(tourPackageDetailPage.getPageable().getPageNumber(),
						tourPackageDetailPage.getPageable().getPageSize()),
				tourPackageDetailPage.getTotalElements()
		);
		
		return tourPackageDetailPageList;
	}
	
}