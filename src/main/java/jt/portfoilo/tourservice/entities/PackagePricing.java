package jt.portfoilo.tourservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/*
CREATE TABLE package_pricing (
    pid int NOT NULL,
    room_type varchar(15) NOT NULL,
    room_price int NOT NULL,
    tour_package_detail_tt_id bigint NOT NULL,
    CONSTRAINT package_pricing_pk PRIMARY KEY (pid)
);
 */

@Data
@Entity
@Table(name = "package_pricing")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PackagePricing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @NotNull
    @Enumerated
    @Column(name = "room_type")
    private RoomTypeEnum roomType;

    @NotNull
    @Column(name = "room_price")
    private Double roomPrice;
    
    @ManyToOne
    private TourPackageDetail tourPackageDetail;
    
    public PackagePricing(RoomTypeEnum roomType, Double roomPrice) {
        this.pid = pid;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }
}
