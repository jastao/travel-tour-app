package jt.portfoilo.tourservice.web.rest.mapper;

import jt.portfoilo.tourservice.domain.DailyTourDetail;
import jt.portfoilo.tourservice.web.rest.model.DailyTourDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by JT on 3/31/2019
 */
@Mapper(componentModel = "spring", uses = {
		DateMapper.class
})
public interface DailyTourDetailMapper {

	@Mapping(source = "tourPackageDetail.id", target = "tourPackageDetailId")
	DailyTourDetailDTO dailyTourDetailToDto(final DailyTourDetail dailyTourDetail);

	@Mapping(source = "tourPackageDetailId", target = "tourPackageDetail.id")
	DailyTourDetail dtoToDailyTourDetail(final DailyTourDetailDTO dailyTourDetailDTO);

}
