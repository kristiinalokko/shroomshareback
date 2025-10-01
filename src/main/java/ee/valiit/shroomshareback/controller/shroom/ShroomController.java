package ee.valiit.shroomshareback.controller.shroom;

import ee.valiit.shroomshareback.controller.shroom.dto.*;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomInfo;
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
    public void addShroom(@RequestParam Integer userId, @RequestBody ShroomDto shroomDto) {
        shroomService.addShroom(userId, shroomDto);
    }

    @PutMapping("/shroom")
    @Operation(summary = "Update shroom")
    public void updateShroom(@RequestParam Integer shroomId, @RequestBody ShroomDto shroomDto) {
        shroomService.updateShroom(shroomId, shroomDto);
    }

    @DeleteMapping("/shroom")
    @Operation(summary = "Delete shroom")
    public void deleteShroom(@RequestParam Integer shroomId){
        shroomService.deleteShroom(shroomId);
    }

    @GetMapping("/shrooms")
    @Operation(summary = "Get all shrooms")
    public List<ShroomInfo> getShrooms() {
        return shroomService.getShrooms();
    }

    @GetMapping("/shroom/detailed")
    @Operation(summary = "Get shroom detailed info")
    public ShroomDetailedInfo getShroomDetailedInfo(@RequestParam Integer shroomId) {
        return shroomService.getShroomDetailedInfo(shroomId);
    }



    @GetMapping("/shrooms/detailed/all")
    @Operation(summary = "Gets information for the shroom table, returns a list")
    public List<ShroomDetailedInfo> getAllShroomsDetailedInfo() {
        return shroomService.getAllShroomsDetailedInfo();
    }





}
