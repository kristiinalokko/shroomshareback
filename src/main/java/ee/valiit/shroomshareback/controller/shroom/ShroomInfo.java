package ee.valiit.shroomshareback.controller.shroom;

import ee.valiit.shroomshareback.controller.location.dto.SimplifiedLocation;
import ee.valiit.shroomshareback.persistence.shroom.Shroom;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomLocation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Shroom}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShroomInfo implements Serializable {

    @NotNull
    private Integer userId;
    @NotNull
    @Size(max = 255)
    private String shroomName;
    @Size(max = 255)
    private String shroomDescription;

    private String shroomImage;

    private List<SimplifiedLocation> locations;
}