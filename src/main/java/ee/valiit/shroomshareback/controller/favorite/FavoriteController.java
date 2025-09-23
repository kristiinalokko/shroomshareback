package ee.valiit.shroomshareback.controller.favorite;

import ee.valiit.shroomshareback.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/favorite")
    public Boolean getFavorite(@RequestParam Integer locationId, @RequestParam Integer userId) {
        return favoriteService.getFavorite(locationId, userId);
    }

    @PutMapping("/updateFavorite")
    public void updateFavorite(FavoriteDto favoriteDto) {
        favoriteService.updateFavorite(favoriteDto);
    }


}
