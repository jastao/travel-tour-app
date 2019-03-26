package jt.portfoilo.tourservice.repository;

import jt.portfoilo.tourservice.entities.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by JT on 3/24/2019
 */
@RepositoryRestResource
public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {
	
	Optional<TourPackage> findByTourCode(String tourCode);
}
