package ee.valiit.shroomshareback.controller.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import ee.valiit.shroomshareback.controller.location.dto.LocationShortInfo;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomBasicInfo;
import ee.valiit.shroomshareback.service.LocationService;
import ee.valiit.shroomshareback.service.ShroomLocationService;
import ee.valiit.shroomshareback.service.ShroomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final ShroomService shroomService;


    @GetMapping("/location-info")
    @Operation(summary = "Get user location info based on locationId and the status code")
    public LocationInfo getLocationInfoIfActive(@RequestParam Integer locationId, @RequestParam String status) {
        return locationService.getLocationInfoByIdAndStatus(locationId, status);
    }

    @PostMapping("/location")
    @Operation(summary = "add new location")
    public void addLocation(@RequestBody LocationDto locationDto) {
        locationService.addLocation(locationDto);
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

}
