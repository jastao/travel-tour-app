package jt.portfoilo.tourservice.web.rest.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
public class DailyTourDetailDTO extends BaseItem {

    @Builder
    public DailyTourDetailDTO(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                              UUID tourPackageDetailId, Integer dayOfTrip, String description) {
        super(id, version, createdDate, lastModifiedDate);
        this.tourPackageDetailId = tourPackageDetailId;
        this.dayOfTrip = dayOfTrip;
        this.description = description;
    }
    
    private UUID tourPackageDetailId;

    private Integer dayOfTrip;

    private String description;
}
