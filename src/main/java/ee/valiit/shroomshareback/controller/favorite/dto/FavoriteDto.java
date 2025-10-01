package ee.valiit.shroomshareback.controller.favorite.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ee.valiit.shroomshareback.persistence.favorite.Favorite}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto implements Serializable {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer locationId;

}