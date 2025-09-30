package ee.valiit.shroomshareback.controller.shroomlocation.dto;

import ee.valiit.shroomshareback.controller.shroom.dto.ShroomInfo;
import ee.valiit.shroomshareback.service.ShroomLocationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShroomLocationController {

    private final ShroomLocationService shroomLocationService;

    @PostMapping("/location/shroom")
    @Operation(summary = "Add shroom to location")
    public void addShroomLocation(@RequestParam Integer locationId, @RequestParam Integer shroomId) {
        shroomLocationService.addShroomLocation(locationId, shroomId);
    }

    @DeleteMapping("/location/shroom")
    @Operation(summary = "Delete shroom location")
    public void deleteShroomLocation(@RequestParam Integer locationId, @RequestParam Integer shroomId) {
        shroomLocationService.deleteShroomLocation(locationId, shroomId);
    }

    @GetMapping("/location/shrooms")
    @Operation(summary = "Get all location shrooms")
    public List<ShroomInfo> getLocationShrooms(@RequestParam Integer locationId) {
        return shroomLocationService.getLocationShrooms(locationId);
    }

}
