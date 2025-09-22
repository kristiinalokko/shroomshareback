package ee.valiit.shroomshareback.controller.location.dto;

import ee.valiit.shroomshareback.persistence.location.Location;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Location}
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfo extends LocationDto implements Serializable {
    private String username;
    private String createdAt;
    private BigDecimal avgRating;
}