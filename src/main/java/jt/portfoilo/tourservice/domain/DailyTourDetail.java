package jt.portfoilo.tourservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "daily_tour_detail")
public class DailyTourDetail extends BaseEntity {

    @Builder
    public DailyTourDetail(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                           Integer dayOfTrip, String description, TourPackageDetail tourPackageDetail) {
        super(id, version, createdDate, lastModifiedDate);
        this.dayOfTrip = dayOfTrip;
        this.description = description;
        this.tourPackageDetail = tourPackageDetail;
    }
    
    @NotNull
    @Column(name = "day_of_trip")
    private Integer dayOfTrip;

    @NotNull
    @Size( max = 300)
    private String description;

    @ManyToOne
    @JoinColumn(name = "tour_package_detail_id")
    private TourPackageDetail tourPackageDetail;

}
