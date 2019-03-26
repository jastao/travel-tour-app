package jt.portfoilo.tourservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by JT on 3/22/2019
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tour_package")
public class TourPackage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "tour_code")
	private String tourCode;
	
	@NotNull
	@Column(name = "city_of_interest")
	private String cityOfInterest;

	@OneToOne(mappedBy = "tourPackage")
	private TourPackageDetail tourPackageDetail;

	public TourPackage(String tourCode, String cityOfInterest) {
		this.tourCode = tourCode;
		this.cityOfInterest = cityOfInterest;
	}
	
	public void removeTourPackageDetail() {
		this.setTourPackageDetail(null);
	}
}