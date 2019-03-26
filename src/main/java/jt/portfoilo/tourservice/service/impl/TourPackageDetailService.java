package jt.portfoilo.tourservice.service.impl;

import jt.portfoilo.tourservice.entities.TourPackageDetail;
import jt.portfoilo.tourservice.service.dto.TourPackageDetailDTO;
import jt.portfoilo.tourservice.entities.TourPackage;
import jt.portfoilo.tourservice.repository.TourPackageDetailRepository;
import jt.portfoilo.tourservice.repository.TourPackageRepository;
import jt.portfoilo.tourservice.service.ITourPackageDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by JT on 3/24/2019
 */
@Service
@Transactional
public class TourPackageDetailService implements ITourPackageDetailService {
	
	private Logger logger = LoggerFactory.getLogger(TourPackageDetailService.class);
	
	private final TourPackageRepository tourPackageRepository;
	private final TourPackageDetailRepository tourPackageDetailRepository;
	
	public TourPackageDetailService(TourPackageRepository tourPackageRepository, TourPackageDetailRepository tourPackageDetailRepository) {
		this.tourPackageRepository = tourPackageRepository;
		this.tourPackageDetailRepository = tourPackageDetailRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public TourPackageDetailDTO tourPackageByTourCode(String tourCode) throws Exception {
	
		TourPackage tourPackage = this.tourPackageRepository.findByTourCode(tourCode)
					.orElseThrow( () -> new Exception("Tour Code not found."));
		
		 TourPackageDetail tourPackageDetail
				 = this.tourPackageDetailRepository.findByTourPackage(tourPackage);
		
		TourPackageDetailDTO tourPackageDetailDTO = convert(tourCode, tourPackageDetail);
		return tourPackageDetailDTO;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<TourPackageDetailDTO> tourPackagesByDepCity(String depCity, int page, int size) {
		
		Page<TourPackageDetail> tourPackageDetails
				= this.tourPackageDetailRepository.findByDepartureCity(depCity, PageRequest.of(page, size));
		
		Page<TourPackageDetailDTO> tourPackageDetailDTO = convert(tourPackageDetails);
		return tourPackageDetailDTO;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<TourPackageDetailDTO> tourPackages(int page, int size) {
		
		Page<TourPackageDetail> tourPackageDetails
				= this.tourPackageDetailRepository.findAll(PageRequest.of(page, size));
		
		Page<TourPackageDetailDTO> tourPackageDetailDTO = convert(tourPackageDetails);
		return tourPackageDetailDTO;
	}
	
	private TourPackageDetailDTO convert( String tourCode, TourPackageDetail tourPackageDetail) {
		
		TourPackageDetailDTO tpdDTO = TourPackageDetailDTO.builder()
					.tourCode(!tourCode.isEmpty() ? tourCode : null)
					.description(tourPackageDetail.getDescription())
					.tourTitle(tourPackageDetail.getTourTitle())
					.duration(tourPackageDetail.getDuration())
					.departureCity(tourPackageDetail.getDepartureCity())
					.offerStartDate(tourPackageDetail.getOfferStartDate())
					.offerEndDate(tourPackageDetail.getOfferEndDate())
					.departureDateInWeek(tourPackageDetail.getDepartureDateInWeek())
					.build();
			
		return tpdDTO;
	}
	
	private Page<TourPackageDetailDTO> convert(Page<TourPackageDetail> tourPackageDetails) {
		
		return tourPackageDetails.map( (tourPackageDetail) -> {
			
			String tourCode
					= this.tourPackageRepository.findById(tourPackageDetail.getTourPackage().getId()).get().getTourCode();
			
			TourPackageDetailDTO tpdDTO = TourPackageDetailDTO.builder()
					.tourCode(tourCode)
					.description(tourPackageDetail.getDescription())
					.tourTitle(tourPackageDetail.getTourTitle())
					.duration(tourPackageDetail.getDuration())
					.departureCity(tourPackageDetail.getDepartureCity())
					.offerStartDate(tourPackageDetail.getOfferStartDate())
					.offerEndDate(tourPackageDetail.getOfferEndDate())
					.departureDateInWeek(tourPackageDetail.getDepartureDateInWeek())
					.build();
			
			return tpdDTO;
		});
	}
}
