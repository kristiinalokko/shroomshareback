package ee.valiit.shroomshareback.controller.location.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ee.valiit.shroomshareback.persistence.location.Location}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationTableInfo extends SimplifiedLocation implements Serializable {
    //simplifiedLocationist tuleb id ja locationName
    private Integer userId;
    private String username;
    @Size(max = 255)
    private String description;
    @NotNull
    private String status;
}