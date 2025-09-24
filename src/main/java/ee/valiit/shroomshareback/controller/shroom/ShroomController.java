package ee.valiit.shroomshareback.controller.shroom;

import ee.valiit.shroomshareback.service.ShroomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShroomController {

    private final ShroomService shroomService;

    @GetMapping("/shroom")
    @Operation(summary = "Get shroom info")
    public ShroomInfo getShroom(@RequestParam Integer shroomId){
        return shroomService.getShroom(shroomId);
    }
}
