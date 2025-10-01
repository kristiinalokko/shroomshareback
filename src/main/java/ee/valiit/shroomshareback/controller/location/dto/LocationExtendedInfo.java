package ee.valiit.shroomshareback.controller.location.dto;

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
public class LocationExtendedInfo implements Serializable {
    private Integer locationId;
    private String locationName;
    private Integer userId;
    private String username;
    private String description;
    private String status;
}