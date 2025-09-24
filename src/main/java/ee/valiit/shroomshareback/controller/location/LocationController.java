package ee.valiit.shroomshareback.controller.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import ee.valiit.shroomshareback.controller.location.dto.LocationShortInfo;
import ee.valiit.shroomshareback.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    // Get user location info based on locationId
    @GetMapping("/location-info")
    @Operation(summary = "Get user location info")
    public LocationInfo getLocationInfo(@RequestParam Integer locationId) {
        return locationService.getLocationInfo(locationId);
    }

    // Add new location
    @PostMapping("/location")
    @Operation(summary = "add new location")
    public void addLocation(@RequestBody LocationDto locationDto) {
        locationService.addLocation(locationDto);
    }

    // Filter locations based on multiple criteria
    @GetMapping("/locations/filter")
    @Operation(summary = "Filter locations based on user input criteria")
    public List<LocationInfo> filterLocations(
            @RequestParam(required = false) String shroomName,
            @RequestParam(required = false) BigDecimal rating,
            @RequestParam(required = false) String lastActiveAfter,
            @RequestParam(required = false) BigDecimal lat,
            @RequestParam(required = false) BigDecimal lon,
            @RequestParam(required = false) BigDecimal radius
    ) {
        return locationService.filterLocations(shroomName, rating, lastActiveAfter, lat, lon, radius);
    }


    @GetMapping("/map-locations/all")
    public List<LocationShortInfo> findAllLocations() {
        List<LocationShortInfo> allLocations = locationService.findAllLocations();
        return allLocations;
    }

}