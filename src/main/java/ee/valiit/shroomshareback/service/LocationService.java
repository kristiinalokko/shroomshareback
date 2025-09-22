package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
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
import java.time.LocalDateTime;
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
        Optional<User> optionalUser = userRepository.findById(locationDto.getUserId());
        Location location = locationMapper.dtoToLocation(locationDto);

        User user = optionalUser.get();
        location.setUser(user);

        location.setStatus(Status.PENDING.getCode());

        location.setLastActive(LocalDate.now());
        location.setAvgRating(BigDecimal.valueOf(0.0));

        locationRepository.save(location);

        if(!locationDto.getLocationImage().isEmpty()){
            byte[] locationImage = BytesConverter.stringToBytes(locationDto.getLocationImage());
            LocationImage locationImage1 = new LocationImage();
            locationImage1.setLocation(location);
            locationImage1.setImageData(locationImage);
            locationImageRepository.save(locationImage1);
        }
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
}
