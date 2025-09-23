package ee.valiit.shroomshareback.controller.favorite;

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
    private Boolean isFavorite;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer locationId;

}