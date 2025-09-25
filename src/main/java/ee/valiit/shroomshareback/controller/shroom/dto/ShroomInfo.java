package ee.valiit.shroomshareback.controller.shroom.dto;

import ee.valiit.shroomshareback.controller.location.dto.SimplifiedLocation;
import ee.valiit.shroomshareback.persistence.shroom.Shroom;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name;
    @Size(max = 255)
    private String description;

    @NotNull
    private String shroomImage;

    private List<SimplifiedLocation> locations;
}