package ee.valiit.shroomshareback.controller.register;

import ee.valiit.shroomshareback.controller.register.dto.RegisterInfo;
import ee.valiit.shroomshareback.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public void addUser(@RequestBody RegisterInfo registerInfo) {
        registrationService.addUser(registerInfo);
    }

}
