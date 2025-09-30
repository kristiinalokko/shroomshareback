package ee.valiit.shroomshareback.controller.shroom.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShroomDto implements Serializable {

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    private String shroomImage;

}