package ee.valiit.shroomshareback.controller.location.dto;

import ee.valiit.shroomshareback.persistence.location.Location;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link Location}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto implements Serializable {

    @NotNull
    private Integer userId;

    @NotNull
    @Size(max = 255)
    private String locationName;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

    @Size(max = 255)
    private String description;

    @NotNull
    private String locationImage;

}