package ee.valiit.shroomshareback.controller.location.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ee.valiit.shroomshareback.persistence.location.Location}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationTableInfo implements Serializable {
    private Integer locationId;
    private String locationName;
    private Integer userId;
    private String username;
    private String description;
    private String status;
}