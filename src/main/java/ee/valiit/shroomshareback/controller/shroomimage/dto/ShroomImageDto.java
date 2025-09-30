package ee.valiit.shroomshareback.controller.shroomimage.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShroomImageDto implements Serializable {
    @NotNull
    private String imageData;
}