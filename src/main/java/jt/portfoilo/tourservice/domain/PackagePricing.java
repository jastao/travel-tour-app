package jt.portfoilo.tourservice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "package_pricing")
public class PackagePricing extends BaseEntity {

    @Builder
    public PackagePricing(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                          RoomTypeEnum roomType, Double roomPrice, TourPackageDetail tourPackageDetail) {
        super(id, version, createdDate, lastModifiedDate);
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.tourPackageDetail = tourPackageDetail;
    }
    
    @NotNull
    @Column(name = "room_type")
    private RoomTypeEnum roomType = RoomTypeEnum.SINGLE_ROOM;

    @NotNull
    @Column(name = "room_price")
    private Double roomPrice;
    
    @ManyToOne
    @JoinColumn(name = "tour_package_detail_id")
    private TourPackageDetail tourPackageDetail;
    
}
