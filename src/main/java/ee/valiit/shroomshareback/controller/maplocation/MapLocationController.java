package ee.valiit.shroomshareback.controller.maplocation;

import ee.valiit.shroomshareback.controller.location.dto.LocationMapInfo;
import ee.valiit.shroomshareback.controller.maplocation.dto.MapLocationsRequest;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomInfo;
import ee.valiit.shroomshareback.service.MapLocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MapLocationController {

    private final MapLocationService mapLocationService;

    @GetMapping("/map-locations/all")
    public List<LocationMapInfo> findAllLocations() {
        return mapLocationService.findAllLocations();

    }


    @GetMapping("/map-locations/shroom")
    public List<LocationMapInfo> findShroomLocations(Integer shroomId) {
        return mapLocationService.findShroomLocations(shroomId);
    }


    @GetMapping("/map-locations/filtered")
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
    public List<LocationMapInfo> findFilteredShroomLocations(
            @RequestParam(required = false) Integer shroomId,
            @RequestParam(required = false, defaultValue = "0.0") BigDecimal minRating,
            @RequestParam(required = false, defaultValue = "") String lastActive,
            @RequestParam BigDecimal latitude,
            @RequestParam BigDecimal longitude,
            @RequestParam Integer radiusKm
    ) {

        LocalDate localDate;
        if (lastActive.isEmpty()) {
            localDate = LocalDate.of(1900, 1, 1);
        } else {
            localDate = LocalDate.parse(lastActive);
        }


        MapLocationsRequest mapLocationsRequest = MapLocationsRequest.builder()
                .shroomId(shroomId)
                .minRating(minRating)
                .lastActive(localDate)
                .latitude(latitude)
                .longitude(longitude)
                .radiusKm(radiusKm)
                .build();


        return mapLocationService.findFilteredShroomLocations(mapLocationsRequest);
    }

}
