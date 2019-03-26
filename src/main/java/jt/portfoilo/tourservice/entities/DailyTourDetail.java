package jt.portfoilo.tourservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/*
CREATE TABLE daily_tour_detail (
    id int NOT NULL,
    day_of_trip int NOT NULL,
    description varchar(200) NOT NULL,
    tour_package_detail_tt_id bigint NOT NULL,
    CONSTRAINT daily_tour_detail_pk PRIMARY KEY (id)
);
 */

@Data
@Entity
@Table(name = "daily_tour_detail")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class DailyTourDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "day_of_trip")
    private Integer dayOfTrip;

    @NotNull
    @Size( max = 100)
    private String description;

    @ManyToOne
    private TourPackageDetail tourPackageDetail;
    
    public DailyTourDetail(Integer dayOfTrip, String description) {
        this.dayOfTrip = dayOfTrip;
        this.description = description;
    }
}
