package ee.valiit.shroomshareback.controller.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import ee.valiit.shroomshareback.controller.location.dto.LocationShortInfo;
import ee.valiit.shroomshareback.controller.location.dto.LocationTableInfo;
import ee.valiit.shroomshareback.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;


    @GetMapping("/location-info")
    @Operation(summary = "Get user location info, returns location info with status")
    public LocationInfo getLocationInfo(@RequestParam Integer locationId) {
        return locationService.getLocationInfo(locationId);
    }

    @PostMapping("/location")
    @Operation(summary = "add new location")
    public Integer addLocation(@RequestBody LocationDto locationDto) {
        return locationService.addLocation(locationDto);
    }

    @PutMapping("/location")
    @Operation(summary = "Edit location info")
    public void editLocation(@RequestBody LocationDto locationDto, @RequestParam Integer locationId) {
        locationService.editLocation(locationDto, locationId);
    }

    @PutMapping("/location-info")
    @Operation(summary = "Kustutab/deaktiveerib asukoha databaasis")
    public void deactivateLocation(@RequestParam Integer locationId) {
        locationService.deactivateLocation(locationId);
    }

    @GetMapping("/map-locations/all")
    public List<LocationShortInfo> findAllLocations() {
        return locationService.findAllLocations();
    }

    @GetMapping("/locationsTable")
    public List<LocationTableInfo> getAllLocationTableInfos() {
        return locationService.getAllLocationTableInfos();
    }
}
