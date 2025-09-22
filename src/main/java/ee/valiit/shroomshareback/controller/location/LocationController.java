package ee.valiit.shroomshareback.controller.location;

import ee.valiit.shroomshareback.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private LocationService locationService;

    @GetMapping("/location")
    @Operation(summary = "Get user location")
    public void getLocation(@RequestParam Integer locationId) {
        locationService.getLocation(locationId);
    }

}
