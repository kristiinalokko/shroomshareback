package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import ee.valiit.shroomshareback.infrastructure.exception.DataNotFoundException;
import ee.valiit.shroomshareback.persistence.favorite.Favorite;
import ee.valiit.shroomshareback.persistence.favorite.FavoriteRepository;
import ee.valiit.shroomshareback.persistence.location.Location;
import ee.valiit.shroomshareback.persistence.location.LocationMapper;
import ee.valiit.shroomshareback.persistence.location.LocationRepository;
import ee.valiit.shroomshareback.persistence.locationImge.LocationImage;
import ee.valiit.shroomshareback.persistence.locationImge.LocationImageRepository;
import ee.valiit.shroomshareback.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final LocationImageRepository locationImageRepository;
    private final FavoriteRepository favoriteRepository;

    public LocationInfo getLocationInfo(Integer locationId, Integer userId) {
        Optional<Location> optionalLocation = locationRepository.findById(locationId);

        if (optionalLocation.isPresent()) {

            LocationInfo locationInfo = locationMapper.toLocationInfo(optionalLocation.get());

            Optional<LocationImage> optionalImage = locationImageRepository.findImageByLocationId(locationId);
            if (optionalImage.isPresent()) {
                locationInfo.setLocationImage(BytesConverter.bytesToString(optionalImage.get().getImageData()));
            }else {
                locationInfo.setLocationImage("");
            }

            Optional<Favorite> optionalFavorite = favoriteRepository.findByUserId(locationId, userId);
            locationInfo.setIsFavourite(optionalFavorite.isPresent());

            return locationInfo;

        } else {
            throw new DataNotFoundException(Error.LOCATION_NOT_FOUND.getMessage(), Error.LOCATION_NOT_FOUND.getErrorCode());
        }
    }
}
