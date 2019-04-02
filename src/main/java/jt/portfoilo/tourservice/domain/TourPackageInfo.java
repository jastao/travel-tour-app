package jt.portfoilo.tourservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by JT on 3/22/2019
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tour_package")
public class TourPackageInfo extends BaseEntity {
	
	@Builder
	public TourPackageInfo(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
						   String tourCode, String cityOfInterest, TourPackageDetail tourPackageDetail) {
		super(id, version, createdDate, lastModifiedDate);
		this.tourCode = tourCode;
		this.cityOfInterest = cityOfInterest;
		this.tourPackageDetail = tourPackageDetail;
	}
	
	@NotNull
	@Column(name = "tour_code")
	private String tourCode;
	
	@NotNull
	@Column(name = "city_of_interest")
	private String cityOfInterest;

	@OneToOne(mappedBy = "tourPackageInfo")
	private TourPackageDetail tourPackageDetail;
	
}