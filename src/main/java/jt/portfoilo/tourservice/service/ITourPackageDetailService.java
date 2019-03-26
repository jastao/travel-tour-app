package jt.portfoilo.tourservice.service;

import jt.portfoilo.tourservice.service.dto.TourPackageDetailDTO;
import org.springframework.data.domain.Page;

/**
 * Created by JT on 3/21/2019
 */
public interface ITourPackageDetailService {

	//TourPackageDetailDTO save(TourPackageDetailDTO tourPackageDetailDTO);

	//void delete(Long id);

	TourPackageDetailDTO tourPackageByTourCode(String tourCode) throws Exception;
	
	Page<TourPackageDetailDTO> tourPackagesByDepCity(String depCity, int page, int size);
	
	Page<TourPackageDetailDTO> tourPackages(int page, int size);
	
}
