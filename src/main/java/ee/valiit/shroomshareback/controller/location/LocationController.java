package ee.valiit.shroomshareback.controller.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import ee.valiit.shroomshareback.controller.location.dto.LocationShortInfo;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomBasicInfo;
import ee.valiit.shroomshareback.service.LocationService;
import ee.valiit.shroomshareback.service.ShroomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final ShroomService shroomService;

    @GetMapping("/location-info")
    @Operation(summary = "Get user location info, but only if the location is ACTIVE")
    public LocationInfo getLocationInfoIfActive(@RequestParam Integer locationId) {
        return locationService.getLocationInfoIfActive(locationId);
    }

    @PostMapping("/location")
    @Operation(summary = "add new location")
    public void addLocation(@RequestBody LocationDto locationDto){
        locationService.addLocation(locationDto);
    }

    @PutMapping("/location")
    @Operation(summary = "Edit location info")
    public void editLocation(@RequestBody LocationDto locationDto, @RequestParam Integer locationId){
        locationService.editLocation(locationDto, locationId);
    }
    @GetMapping("/map-locations/all")
    public List<LocationShortInfo> findAllLocations() {
        return locationService.findAllLocations();
    }
    @GetMapping("/location/shrooms")
    @Operation(summary = "Get all location shrooms")
    public List<ShroomBasicInfo> getLocationShrooms(@RequestParam Integer locationId) {
        return shroomService.getLocationShrooms(locationId);
    }
}
