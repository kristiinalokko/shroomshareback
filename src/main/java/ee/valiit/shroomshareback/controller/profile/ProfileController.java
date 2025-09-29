package ee.valiit.shroomshareback.controller.profile;

import ee.valiit.shroomshareback.controller.profile.dto.ProfileData;
import ee.valiit.shroomshareback.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profile")
    public ProfileData getProfile(@RequestParam Integer userId) {
        return profileService.getProfile(userId);
    }

}
