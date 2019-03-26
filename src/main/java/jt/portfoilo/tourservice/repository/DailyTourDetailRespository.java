package jt.portfoilo.tourservice.repository;

import jt.portfoilo.tourservice.entities.DailyTourDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by JT on 3/24/2019
 */
@RepositoryRestResource
public interface DailyTourDetailRespository extends JpaRepository<DailyTourDetail, Long> {

}