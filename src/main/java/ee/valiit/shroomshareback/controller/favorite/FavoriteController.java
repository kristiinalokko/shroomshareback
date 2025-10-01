package ee.valiit.shroomshareback.controller.favorite;

import ee.valiit.shroomshareback.controller.favorite.dto.FavoriteDto;
import ee.valiit.shroomshareback.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/favorite")
    @Operation(summary = "tagastab booleani, kas asukoht on kasutaja lemmikuks lisatud")
    public Boolean getFavorite(@RequestParam Integer locationId, @RequestParam Integer userId) {
        return favoriteService.getFavorite(locationId, userId);
    }

    @PostMapping("/favorite")
    @Operation(summary = "lisab lemmikuks")
    public void addFavorite(@RequestParam Integer userId, @RequestParam Integer locationId) {
        favoriteService.addFavorite(userId, locationId);
    }

    @DeleteMapping("/favorite")
    @Operation(summary = "eemaldab lemmikute hulgast")
    public void deleteFavorite(FavoriteDto favoriteDto) {
        favoriteService.deleteFavorite(favoriteDto);
    }



}
