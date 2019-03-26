package jt.portfoilo.tourservice.repository;

import jt.portfoilo.tourservice.entities.TourPackage;
import jt.portfoilo.tourservice.entities.TourPackageDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Craeted by JT on 3/21/2019
 */
@RepositoryRestResource
public interface TourPackageDetailRepository extends JpaRepository<TourPackageDetail, Long> {

	TourPackageDetail findByTourPackage(TourPackage tourPkg);

	Page<TourPackageDetail> findByDepartureCity(String depCity, Pageable pageable);

}
