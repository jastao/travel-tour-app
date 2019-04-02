package jt.portfoilo.tourservice.repositories;

import jt.portfoilo.tourservice.domain.DailyTourDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

/**
 * Created by JT on 3/24/2019
 */
@RepositoryRestResource
public interface DailyTourDetailRespository extends PagingAndSortingRepository<DailyTourDetail, UUID> {

}
