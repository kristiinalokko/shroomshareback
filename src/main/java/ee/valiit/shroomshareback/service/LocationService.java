package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.controller.location.dto.LocationExtendedInfo;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import ee.valiit.shroomshareback.infrastructure.exception.DataNotFoundException;
import ee.valiit.shroomshareback.persistence.favorite.Favorite;
import ee.valiit.shroomshareback.persistence.favorite.FavoriteRepository;
import ee.valiit.shroomshareback.persistence.location.Location;
import ee.valiit.shroomshareback.persistence.location.LocationMapper;
import ee.valiit.shroomshareback.persistence.location.LocationRepository;
import ee.valiit.shroomshareback.persistence.locationImge.LocationImage;
import ee.valiit.shroomshareback.persistence.locationImge.LocationImageRepository;
import ee.valiit.shroomshareback.persistence.user.User;
import ee.valiit.shroomshareback.persistence.user.UserRepository;
import ee.valiit.shroomshareback.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final LocationImageRepository locationImageRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;


    public Integer addLocation(LocationDto locationDto) {
        Location location = setLocationData(locationDto);
        locationRepository.save(location);
        saveLocationImageIfPresent(locationDto, location);
        return location.getId();
    }

    public void editLocation(LocationDto locationDto, Integer locationId) {
        Location location = locationRepository.findById(locationId).
                orElseThrow();
        locationMapper.updateLocation(location, locationDto);
        location.setStatus(Status.PENDING.getCode());
        locationRepository.save(location);
        saveLocationImageIfPresent(locationDto, location);
    }


    public LocationInfo getLocationInfo(Integer locationId) {
        Optional<Location> optionalLocation = locationRepository.findById(locationId);

        if (optionalLocation.isPresent()) {
            return createAndSetLocationInfo(locationId, optionalLocation.get());
        } else {
            throw new DataNotFoundException(Error.LOCATION_NOT_FOUND.getMessage(), Error.LOCATION_NOT_FOUND.getErrorCode());
        }
    }

    private LocationInfo createAndSetLocationInfo(Integer locationId, Location location) {
        LocationInfo locationInfo = locationMapper.toLocationInfo(location);
        addImageIfExists(locationId, locationInfo);
        return locationInfo;
    }

    private Location setLocationData(LocationDto locationDto) {
        Location location = locationMapper.toLocation(locationDto);
        User user = findUser(locationDto);
        location.setUser(user);
        setLocationDefaultValues(location);
        return location;
    }

    private static void setLocationDefaultValues(Location location) {
        location.setStatus(Status.PENDING.getCode());
        location.setCreated(LocalDate.now());
        location.setAvgRating(BigDecimal.valueOf(0.0));
    }

    private void saveLocationImageIfPresent(LocationDto locationDto, Location location) {
        if (locationImageIsPresent(locationDto)) {
            createAndSaveLocationImage(locationDto, location);
        }
    }

    private void createAndSaveLocationImage(LocationDto locationDto, Location location) {
        Optional<LocationImage> optionalImage = locationImageRepository.findImageByLocationId(location.getId());
        byte[] imageData = BytesConverter.stringToBytes(locationDto.getLocationImage());

        if (optionalImage.isPresent()) {
            LocationImage locationImage = optionalImage.get();
            locationImage.setImageData(imageData);
            locationImageRepository.save(locationImage);
        } else {
            LocationImage locationImage = createLocationImage(location, imageData);
            locationImageRepository.save(locationImage);
        }

    }

    private static LocationImage createLocationImage(Location location, byte[] imageData) {
        LocationImage locationImage = new LocationImage();
        locationImage.setLocation(location);
        locationImage.setImageData(imageData);
        return locationImage;
    }

    private static boolean locationImageIsPresent(LocationDto locationDto) {
        return !locationDto.getLocationImage().isEmpty();
    }

    private User findUser(LocationDto locationDto) {
        Optional<User> optionalUser = userRepository.findById(locationDto.getUserId());
        return optionalUser.get();
    }

    private void addImageIfExists(Integer locationId, LocationInfo locationInfo) {
        Optional<LocationImage> optionalImage = locationImageRepository.findImageByLocationId(locationId);
        if (optionalImage.isPresent()) {
            locationInfo.setLocationImage(BytesConverter.bytesToString(optionalImage.get().getImageData()));
        } else {
            locationInfo.setLocationImage("");
        }
    }

    public void deactivateLocation(Integer locationId) {
        locationRepository.updateStatusBy(Status.DEACTIVATED.getCode(), locationId);
    }

    public List<LocationExtendedInfo> getAllLocationExtendedInfos(Integer userId) {
        List<Location> locations = locationRepository.findAllSorted();
        List<LocationExtendedInfo> locationExtendedInfos = locationMapper.toLocationExtendedInfos(locations);
        for (LocationExtendedInfo locationExtendedInfo : locationExtendedInfos) {
            Optional<Favorite> optionalFavorite = favoriteRepository.findByLocationIdAndUserId(locationExtendedInfo.getLocationId(), userId);
            locationExtendedInfo.setIsFavorite(optionalFavorite.isPresent());
        }
        return locationExtendedInfos;
    }

    public void activateLocation(Integer locationId) {
        locationRepository.updateStatusBy(Status.ACTIVE.getCode(), locationId);
    }
}
