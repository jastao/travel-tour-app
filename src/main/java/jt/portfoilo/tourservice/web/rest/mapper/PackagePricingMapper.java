package jt.portfoilo.tourservice.web.rest.mapper;

import jt.portfoilo.tourservice.domain.PackagePricing;
import jt.portfoilo.tourservice.web.rest.model.PackagePricingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by JT on 3/31/2019
 */
@Mapper(componentModel = "spring", uses = {
		DateMapper.class
})
public interface PackagePricingMapper {

	@Mapping(source = "tourPackageDetail.id", target = "tourPackageDetailId")
	PackagePricingDTO packagePricingToDto(final PackagePricing packagePricing);

	@Mapping(source = "tourPackageDetailId", target = "tourPackageDetail.id")
	PackagePricing dtoToPackagePricing(final PackagePricingDTO packagePricingDTO);

}
