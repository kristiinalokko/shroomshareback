package ee.valiit.shroomshareback.controller.shroom;

import ee.valiit.shroomshareback.controller.shroom.dto.ShroomBasicInfo;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomInfo;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomProfile;
import ee.valiit.shroomshareback.service.ShroomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShroomController {

    private final ShroomService shroomService;

    @PostMapping("/shroom")
    @Operation(summary = "Add shroom")
    public void addShroom(@RequestBody ShroomProfile shroomProfile){
        shroomService.addShroom(shroomProfile);
    }

    @GetMapping("/shroom")
    @Operation(summary = "Get shroom info")
    public ShroomInfo getShroom(@RequestParam Integer shroomId) {
        return shroomService.getShroom(shroomId);
    }

    @GetMapping("/shrooms")
    @Operation(summary = "Get all shrooms")
    public List<ShroomBasicInfo> getShrooms() {
       return shroomService.getShrooms();
    }
}
