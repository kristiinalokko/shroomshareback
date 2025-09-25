package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import ee.valiit.shroomshareback.controller.location.dto.LocationShortInfo;
import ee.valiit.shroomshareback.infrastructure.exception.DataNotFoundException;
import ee.valiit.shroomshareback.persistence.location.Location;
import ee.valiit.shroomshareback.persistence.location.LocationMapper;
import ee.valiit.shroomshareback.persistence.location.LocationRepository;
import ee.valiit.shroomshareback.persistence.locationImge.LocationImage;
import ee.valiit.shroomshareback.persistence.locationImge.LocationImageRepository;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomLocation;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomlocationRepository;
import ee.valiit.shroomshareback.persistence.user.User;
import ee.valiit.shroomshareback.persistence.user.UserRepository;
import ee.valiit.shroomshareback.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final LocationImageRepository locationImageRepository;
    private final UserRepository userRepository;
    private final ShroomlocationRepository shroomlocationRepository;

    public void addLocation(LocationDto locationDto) {
        Location location = setLocationData(locationDto);
        locationRepository.save(location);
        saveLocationImageIfPresent(locationDto, location);
    }

    private Location setLocationData(LocationDto locationDto) {
        Location location = locationMapper.dtoToLocation(locationDto);
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
        if(locationImageIsPresent(locationDto)){
            createAndSaveLocationImage(locationDto, location);
        }
    }

    private void createAndSaveLocationImage(LocationDto locationDto, Location location) {
        byte[] imageData = BytesConverter.stringToBytes(locationDto.getLocationImage());
        LocationImage locationImage = createLocationImage(location, imageData);
        locationImageRepository.save(locationImage);
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

    public LocationInfo getLocationInfoIfActive(Integer locationId) {
        Optional<Location> optionalLocation = locationRepository.findByIdAndStatus(locationId, Status.ACTIVE.getCode());

        if (optionalLocation.isPresent()) {
            LocationInfo locationInfo = locationMapper.toLocationInfo(optionalLocation.get());
            addImageIfExists(locationId, locationInfo);
            return locationInfo;
        } else {
            throw new DataNotFoundException(Error.LOCATION_NOT_FOUND.getMessage(), Error.LOCATION_NOT_FOUND.getErrorCode());
        }
    }

    private void addImageIfExists(Integer locationId, LocationInfo locationInfo) {
        Optional<LocationImage> optionalImage = locationImageRepository.findImageByLocationId(locationId);
        if (optionalImage.isPresent()) {
            locationInfo.setLocationImage(BytesConverter.bytesToString(optionalImage.get().getImageData()));
        } else {
            locationInfo.setLocationImage("");
        }
    }

    public void editLocation(LocationDto locationDto, Integer locationId) {
        Location location = locationRepository.findById(locationId).
                orElseThrow();
        locationMapper.updateLocationFromDto(location, locationDto);
        location.setStatus(Status.PENDING.getCode());
        locationRepository.save(location);
        saveLocationImageIfPresent(locationDto, location);
    }
    public List<LocationShortInfo> findAllLocations() {
        List<Location> locations = locationRepository.findAll();
        List<LocationShortInfo> locationShortInfos = locationMapper.toLocationShortInfos(locations);
        return locationShortInfos;
    }
    public List<LocationShortInfo> findAllShroomLocations(Integer shroomId) {
        List<ShroomLocation> shroomLocations = shroomlocationRepository.findShroomLocationByShroom_Id(shroomId);

        if (shroomLocations.isEmpty()) {
            throw new DataNotFoundException(Error.LOCATION_NOT_FOUND.getMessage(), Error.LOCATION_NOT_FOUND.getErrorCode());
        }

        List<Location> locations = shroomLocations.stream()
                .map(ShroomLocation::getLocation)
                .collect(Collectors.toList());

        return locationMapper.toLocationShortInfos(locations);
    }
}
