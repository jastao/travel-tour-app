package jt.portfoilo.tourservice.web.rest.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
public class TourPackageDTO extends BaseItem {

    public TourPackageDTO(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                          String tourCode, String cityOfInterest) {
        super(id, version, createdDate, lastModifiedDate);
        this.tourCode = tourCode;
        this.cityOfInterest = cityOfInterest;
    }

    private String tourCode;

    private String cityOfInterest;

}
