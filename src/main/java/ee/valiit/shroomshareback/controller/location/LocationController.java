package ee.valiit.shroomshareback.controller.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import ee.valiit.shroomshareback.controller.location.dto.LocationShortInfo;
import ee.valiit.shroomshareback.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

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
        List<LocationShortInfo> allLocations = locationService.findAllLocations();
        return allLocations;
    }
    @GetMapping("/map-locations/by-shroom")
    @Operation(
            summary = "Tagastab konkreetse seene asukohad.",
            description = "Tagastab kõik antud seene asukohad LocationShortInfo kujul"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ei leitud ühtegi seene asukohta"
            )
    })
    public List<LocationShortInfo> findAllShroomLocations(@RequestParam Integer shroomId) {
        return locationService.findAllShroomLocations(shroomId);
    }
}
