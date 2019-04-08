package jt.portfoilo.tourservice.services;

import jt.portfoilo.tourservice.domain.TourPackageDetail;
import jt.portfoilo.tourservice.web.rest.model.TourPackageDetailDTO;
import jt.portfoilo.tourservice.web.rest.model.TourPackageDetailPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Created by JT on 3/21/2019
 */
public interface ITourPackageDetailService {

	TourPackageDetailDTO findDtoById(UUID tourPackageDetailId);

	TourPackageDetail findById(UUID tourPackageDetailId);

	TourPackageDetailDTO save(TourPackageDetailDTO tourPackageDetailDTO);
	
	TourPackageDetailDTO delete(UUID tourPackageId);

	TourPackageDetailDTO tourPackageByTourCode(String tourCode);
	
	TourPackageDetailPagedList tourPackages(String depCity, Pageable request);
	
}
