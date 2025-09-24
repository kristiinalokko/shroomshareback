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

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final LocationImageRepository locationImageRepository;
    private final UserRepository userRepository;

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
        location.setLastActive(LocalDate.now());
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

    public LocationInfo getLocationInfo(Integer locationId) {
        Optional<Location> optionalLocation = locationRepository.findById(locationId);

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

    public List<LocationInfo> filterLocations(String shroomName, BigDecimal rating, String lastActiveAfter, BigDecimal lat, BigDecimal lon, BigDecimal radius) {
        LocalDate lastActiveDate = null;
        if (lastActiveAfter != null && !lastActiveAfter.isEmpty()) {
            try {
                // Assuming the input is in ISO_LOCAL_DATE format (e.g., '2025-09-24')
                lastActiveDate = LocalDate.parse(lastActiveAfter);
            } catch (java.time.format.DateTimeParseException e) {
                // If parsing fails, try a different format
                // Example: 'dd.MM.yyyy'
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    lastActiveDate = LocalDate.parse(lastActiveAfter, formatter);
                } catch (java.time.format.DateTimeParseException ex) {
                    // You can add more formats here if needed or throw a custom exception
                    throw new IllegalArgumentException("Invalid date format for lastActiveAfter: " + lastActiveAfter, ex);
                }
            }
        }
        List<Location> locations = locationRepository.findFilteredLocations(shroomName, rating, lastActiveDate, lat, lon, radius);
        List<LocationInfo> locationInfos = new ArrayList<>();
        for (Location location : locations) {
            LocationInfo info = locationMapper.toLocationInfo(location);
            addImageIfExists(location.getId(), info);
            locationInfos.add(info);
        }
        return locationInfos;
    }

    public List<LocationShortInfo> findAllLocations() {
        List<Location> locations = locationRepository.findAll();
        List<LocationShortInfo> locationShortInfos = locationMapper.toLocationShortInfos(locations);
        return locationShortInfos;
    }
}