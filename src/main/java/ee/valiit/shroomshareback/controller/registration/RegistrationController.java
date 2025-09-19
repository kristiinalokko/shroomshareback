package ee.valiit.shroomshareback.controller.registration;

import ee.valiit.shroomshareback.controller.registration.dto.RegisterInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService regitrationService;

    @PostMapping("/register")
    public void addUser(@RequestBody RegisterInfo registerInfo){
        regitrationService.addUser(registerInfo);
    }

}
