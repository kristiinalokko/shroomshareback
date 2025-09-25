package ee.valiit.shroomshareback.controller.shroom.dto;

import ee.valiit.shroomshareback.persistence.shroom.Shroom;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Shroom}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShroomBasicInfo implements Serializable {

    @NotNull
    private Integer id;
    @NotNull
    @Size(max = 255)
    private String name;
}