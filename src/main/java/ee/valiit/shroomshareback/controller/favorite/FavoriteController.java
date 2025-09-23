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

    @PostMapping("/favorite")
    public void addFavorite(@RequestParam Integer locationId, @RequestParam Integer userId) {
        favoriteService.addFavorite(locationId, userId);
    }

    @DeleteMapping("/favorite")
    public void deleteFavorite(FavoriteDto favoriteDto) {
        favoriteService.deleteFavorite(favoriteDto);
    }



}
