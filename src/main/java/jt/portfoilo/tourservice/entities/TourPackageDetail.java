package jt.portfoilo.tourservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Craeted by JT on 3/20/2019
 */
@Data
@Entity
@Table(name = "tour_package_detail")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TourPackageDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tt_id")
	private Long id;
	
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

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "tour_package_detail_tt_id")
	private Set<DailyTourDetail> dailyTourDetails;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "tour_package_detail_tt_id")
	private Set<PackagePricing> packagePricings;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "tour_package_id")
	private TourPackage tourPackage;
	
	public TourPackageDetail(String description, @NotNull String tourTitle, @NotNull Integer duration, @NotNull String departureCity, Date offerStartDate, Date offerEndDate, DayOfWeek departureDateInWeek) {
		this.description = description;
		this.tourTitle = tourTitle;
		this.duration = duration;
		this.departureCity = departureCity;
		this.offerStartDate = offerStartDate;
		this.offerEndDate = offerEndDate;
		this.departureDateInWeek = departureDateInWeek;
	}
	
	public void addPackagePricingResources(PackagePricing pricing) {
		if(this.packagePricings == null) {
			this.packagePricings = new HashSet<>();
		}
		if(!this.packagePricings.contains(pricing)) {
			this.packagePricings.add(pricing);
		}
	}
	
	public void addDailyTourDetails(DailyTourDetail dailyTourDetail) {
		if(this.dailyTourDetails == null) {
			this.dailyTourDetails = new HashSet<>();
		}
		if(!this.dailyTourDetails.contains(dailyTourDetail)) {
			this.dailyTourDetails.add(dailyTourDetail);
		}
	}
	
	public void removePackagePricingResources(PackagePricing pricing) {
		if(this.packagePricings.contains(pricing)) {
			this.packagePricings.remove(pricing);
		}
	}
	
	public void removeDailyTourDetails(DailyTourDetail dailyTourDetail) {
		if(this.dailyTourDetails.contains(dailyTourDetail)) {
			this.dailyTourDetails.remove(dailyTourDetail);
		}
	}
	
}