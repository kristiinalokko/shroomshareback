package ee.valiit.shroomshareback.controller.shroom.dto;

import ee.valiit.shroomshareback.persistence.shroom.Shroom;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Shroom}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShroomWithUsername extends ShroomDto implements Serializable {
    @NotNull
    private String username;
}