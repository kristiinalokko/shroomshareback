package ee.valiit.shroomshareback.controller.location.dto;

import ee.valiit.shroomshareback.persistence.location.Location;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Location}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationShortInfo implements Serializable {

    private Integer locationId;
    private String locationName;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String username;
    private String createdAt;
    private BigDecimal avgRating;

}