package ee.valiit.shroomshareback.controller.locationimage;

import ee.valiit.shroomshareback.controller.locationimage.dto.LocationImageDto;
import ee.valiit.shroomshareback.service.LocationImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LocationImageController {
    private final LocationImageService locationImageService;

    @GetMapping("/location/image")
    public LocationImageDto findLocationImage(Integer locationId) {
        return locationImageService.findLocationImage(locationId);
    }
}
