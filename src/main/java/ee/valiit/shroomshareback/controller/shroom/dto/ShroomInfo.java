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
public class ShroomInfo implements Serializable {

    @NotNull
    private Integer shroomId;
    @NotNull
    @Size(max = 255)
    private String shroomName;
}