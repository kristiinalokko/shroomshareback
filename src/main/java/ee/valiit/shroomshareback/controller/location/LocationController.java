package ee.valiit.shroomshareback.controller.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import ee.valiit.shroomshareback.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/location-info")
    @Operation(summary = "Get user location info")
    public LocationInfo getLocationInfo(@RequestParam Integer locationId) {
       return locationService.getLocationInfo(locationId);
    }

    @PostMapping("/location")
    @Operation(summary = "add new location")
    public void addLocation(@RequestBody LocationDto locationDto){
        locationService.addLocation(locationDto);
    }

    @PutMapping("/location")
    @Operation(summary = "Edit location info")
    public void editLocation(@RequestBody LocationDto locationDto){
        locationService.editLocation(locationDto);
    }

}
