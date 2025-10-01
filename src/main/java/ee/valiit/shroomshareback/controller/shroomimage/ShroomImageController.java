package ee.valiit.shroomshareback.controller.shroomimage;

import ee.valiit.shroomshareback.controller.shroomimage.dto.ShroomImageDto;
import ee.valiit.shroomshareback.service.ShroomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShroomImageController {

    private final ShroomImageService shroomImageService;

    @GetMapping("/shroom-image")
    public ShroomImageDto getShroomImage(@RequestParam Integer shroomId) {
        return shroomImageService.getShroomImage(shroomId);
    }

}
