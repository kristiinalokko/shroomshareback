package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.controller.favorite.FavoriteDto;
import ee.valiit.shroomshareback.persistence.favorite.Favorite;
import ee.valiit.shroomshareback.persistence.favorite.FavoriteRepository;
import ee.valiit.shroomshareback.persistence.location.Location;
import ee.valiit.shroomshareback.persistence.location.LocationRepository;
import ee.valiit.shroomshareback.persistence.user.User;
import ee.valiit.shroomshareback.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public Boolean getFavorite(Integer locationId, Integer userId) {
        Optional<Favorite> optionalFavorite = favoriteRepository.findByLocationIdAndUserId(locationId, userId);
        return optionalFavorite.isPresent();
    }

    public void updateFavorite(FavoriteDto favoriteDto) {
        User user = userRepository.findById(favoriteDto.getUserId()).get();
        Location location = locationRepository.findById(favoriteDto.getLocationId()).get();

        if (favoriteDto.getIsFavorite()) {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setLocation(location);
            favoriteRepository.save(favorite);
        } else {
            favoriteRepository.deleteFavorite(user, location);
        }
    }
}
