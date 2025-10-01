package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.controller.favorite.dto.FavoriteDto;
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


    public void addFavorite(Integer userId, Integer locationId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Location> optionalLocation = locationRepository.findById(locationId);

        Favorite favorite = new Favorite();
        favorite.setUser(optionalUser.get());
        favorite.setLocation(optionalLocation.get());
        favoriteRepository.save(favorite);
    }


    public void deleteFavorite(FavoriteDto favoriteDto) {
        User user = userRepository.findById(favoriteDto.getUserId()).get();
        Location location = locationRepository.findById(favoriteDto.getLocationId()).get();

        favoriteRepository.deleteByUserAndLocation(user, location);
    }
}
