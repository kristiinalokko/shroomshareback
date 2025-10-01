package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.controller.locationimage.dto.LocationImageDto;
import ee.valiit.shroomshareback.persistence.locationImge.LocationImage;
import ee.valiit.shroomshareback.persistence.locationImge.LocationImageRepository;
import ee.valiit.shroomshareback.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationImageService {

    private final LocationImageRepository locationImageRepository;

    public LocationImageDto findLocationImage(Integer locationId) {
        Optional<LocationImage> optionalImage = locationImageRepository.findImageByLocationId(locationId);
        LocationImageDto locationImageDto = new LocationImageDto();

        if (optionalImage.isPresent()) {
            locationImageDto.setImageData(BytesConverter.bytesToString(optionalImage.get().getImageData()));
        } else {
            locationImageDto.setImageData("");
        }
        return locationImageDto;
    }
}
