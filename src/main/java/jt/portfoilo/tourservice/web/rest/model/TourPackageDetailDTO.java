package jt.portfoilo.tourservice.web.rest.model;

import jt.portfoilo.tourservice.domain.DailyTourDetail;
import lombok.*;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by JT on 3/21/2019
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
public class TourPackageDetailDTO extends BaseItem {

	public TourPackageDetailDTO(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
								UUID tourPackageId, String description, String tourTitle, Integer duration,
								String departureCity, Date offerStartDate, Date offerEndDate, DayOfWeek departureDateInWeek,
								List<DailyTourDetailDTO> dailyTourDetailDTOS, List<PackagePricingDTO> packagePricingDTOS) {
		super(id, version, createdDate, lastModifiedDate);
		this.tourPackageInfoId = tourPackageInfoId;
		this.description = description;
		this.tourTitle = tourTitle;
		this.duration = duration;
		this.departureCity = departureCity;
		this.offerStartDate = offerStartDate;
		this.offerEndDate = offerEndDate;
		this.departureDateInWeek = departureDateInWeek;
		this.dailyTourDetailDTOS = dailyTourDetailDTOS;
		this.packagePricingDTOS = packagePricingDTOS;
	}
	
	private UUID tourPackageInfoId;

	private String description;
	
	private String tourTitle;

	private Integer duration;
	
	private String departureCity;
	
	private Date offerStartDate;
	
	private Date offerEndDate;
	
	private DayOfWeek departureDateInWeek;

	private List<DailyTourDetailDTO> dailyTourDetailDTOS;

	private List<PackagePricingDTO> packagePricingDTOS;

}
