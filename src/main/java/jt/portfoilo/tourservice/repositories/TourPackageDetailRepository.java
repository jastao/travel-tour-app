package jt.portfoilo.tourservice.repositories;

import jt.portfoilo.tourservice.domain.TourPackageInfo;
import jt.portfoilo.tourservice.domain.TourPackageDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;


/**
 * Created by JT on 3/21/2019
 */
@RepositoryRestResource
public interface TourPackageDetailRepository extends JpaRepository<TourPackageDetail, UUID> {

	TourPackageDetail findByTourPackageInfo(TourPackageInfo tourPackageInfo);

	Page<TourPackageDetail> findByDepartureCity(String depCity, Pageable pageable);

}
