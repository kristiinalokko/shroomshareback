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
public class ShroomDetailedInfo implements Serializable {

    private Integer userId;
    private String username;
    private Integer shroomId;
    private String shroomName;
    private String description;
    private String status;

}