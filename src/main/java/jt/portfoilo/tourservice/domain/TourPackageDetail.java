package jt.portfoilo.tourservice.domain;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.*;

/**
 * Created by JT on 3/20/2019
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tour_package_detail")
public class TourPackageDetail extends BaseEntity {
	
	@Builder
	public TourPackageDetail(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
							 String description, @NotNull String tourTitle, @NotNull Integer duration,
							 @NotNull String departureCity, Date offerStartDate, Date offerEndDate,
							 DayOfWeek departureDateInWeek, Set<DailyTourDetail> dailyTourDetails,
							 Set<PackagePricing> packagePricings, TourPackageInfo tourPackageInfo) {
		super(id, version, createdDate, lastModifiedDate);
		this.description = description;
		this.tourTitle = tourTitle;
		this.duration = duration;
		this.departureCity = departureCity;
		this.offerStartDate = offerStartDate;
		this.offerEndDate = offerEndDate;
		this.departureDateInWeek = departureDateInWeek;
		this.dailyTourDetails = dailyTourDetails;
		this.packagePricings = packagePricings;
		this.tourPackageInfo = tourPackageInfo;
	}
	
	private String description;
	
	@NotNull
	@Column(name = "tour_title")
	private String tourTitle;
	
	@NotNull
	private Integer duration;
	
	@NotNull
	@Column(name = "departure_city")
	private String departureCity;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "offer_start_date")
	private Date offerStartDate;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "offer_end_date")
	private Date offerEndDate;
	
	@Enumerated
	@Column(name = "departure_date_in_week")
	private DayOfWeek departureDateInWeek;

	@OneToMany(mappedBy = "tourPackageDetail", cascade = CascadeType.PERSIST)
	@Fetch( FetchMode.JOIN )
	private Set<DailyTourDetail> dailyTourDetails;

	@OneToMany(mappedBy = "tourPackageDetail", cascade = CascadeType.PERSIST)
	@Fetch( FetchMode.JOIN )
	private Set<PackagePricing> packagePricings;
	
	@OneToOne
	@JoinColumn(name = "tour_package_id")
	private TourPackageInfo tourPackageInfo;
	
}