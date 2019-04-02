package jt.portfoilo.tourservice.repositories;

import jt.portfoilo.tourservice.domain.TourPackageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by JT on 3/24/2019
 */
@RepositoryRestResource
public interface TourPackageInfoRepository extends JpaRepository<TourPackageInfo, UUID> {
	
	Optional<TourPackageInfo> findByTourCode(String tourCode);
}
