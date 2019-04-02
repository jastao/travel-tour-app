package jt.portfoilo.tourservice.web.rest.mapper;

import jt.portfoilo.tourservice.domain.TourPackageDetail;
import jt.portfoilo.tourservice.services.impl.TourPackageInfoService;
import jt.portfoilo.tourservice.web.rest.model.TourPackageDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by JT on 3/25/2019
 */
@Mapper( componentModel = "spring", uses = {
		DateMapper.class,
		PackagePricingMapper.class,
		DailyTourDetailMapper.class,
		TourPackageInfoService.class
})
public interface TourPackageDetailMapper {

	@Mappings({
			@Mapping(source = "tourPackageInfo.id", target = "tourPackageInfoId"),
			@Mapping(source = "dailyTourDetails", target = "dailyTourDetailDTOS"),
			@Mapping(source = "packagePricings", target = "packagePricingDTOS")
	})
	TourPackageDetailDTO tourPackageDetailToDto(final TourPackageDetail tourPackageDetail);

	@Mappings({
			@Mapping(source = "tourPackageInfoId", target = "tourPackageInfo.id"),
			@Mapping(source = "dailyTourDetailDTOS", target = "dailyTourDetails"),
			@Mapping(source = "packagePricingDTOS", target = "packagePricings")
	})
	TourPackageDetail dtoToTourPackageDetail(final TourPackageDetailDTO tourPackageDetailDTO);
	
}
