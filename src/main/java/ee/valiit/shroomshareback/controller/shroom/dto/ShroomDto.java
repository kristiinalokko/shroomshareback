package ee.valiit.shroomshareback.controller.shroom.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ee.valiit.shroomshareback.persistence.shroom.Shroom}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShroomDto implements Serializable {
    @NotNull
    private Integer shroomId;
    private Integer userId;
    private String username;
    @NotNull
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String description;
    @NotNull
    private String status;
}