package ee.valiit.shroomshareback.controller.locationimage.dto;

import ee.valiit.shroomshareback.persistence.locationImge.LocationImage;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link LocationImage}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationImageDto implements Serializable {
    @NotNull
    private String imageData;
}