package jt.portfoilo.tourservice.web.rest.model;

import jt.portfoilo.tourservice.domain.RoomTypeEnum;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
public class PackagePricingDTO extends BaseItem {

    @Builder
    public PackagePricingDTO(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                             UUID tourPackageDetailId, RoomTypeEnum roomType, Double roomPrice) {
        super(id, version, createdDate, lastModifiedDate);
        this.tourPackageDetailId = tourPackageDetailId;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }
    
    private UUID tourPackageDetailId;

    private RoomTypeEnum roomType;

    private Double roomPrice;
    
}
