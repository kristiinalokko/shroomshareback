package ee.valiit.shroomshareback.controller.profile;

import ee.valiit.shroomshareback.controller.profile.dto.ProfileData;
import ee.valiit.shroomshareback.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profile")
    public ProfileData getProfile(@RequestParam Integer userId) {
        return profileService.getProfile(userId);
    }

    @PostMapping("/profile")
    @Operation(summary = "add new profile")
    public void addProfile(@RequestBody ProfileData profileData) {
        profileService.addProfile(profileData);
    }

    @PutMapping("/profile")
    @Operation(summary = "Muuda profilli")
    public void updateProfile(@RequestBody ProfileData profileData) {
        profileService.updateProfile(profileData);
    }
}
