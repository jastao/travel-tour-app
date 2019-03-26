package jt.portfoilo.tourservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;
import java.time.DayOfWeek;
import java.util.Date;

/**
 * Created by JT on 3/21/2019
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class TourPackageDetailDTO {
	
	private String tourCode;
	
	private String description;
	
	private String tourTitle;

	private Integer duration;
	
	private String departureCity;
	
	private Date offerStartDate;
	
	private Date offerEndDate;
	
	@Enumerated
	private DayOfWeek departureDateInWeek;

	public TourPackageDetailDTO() {}
}
