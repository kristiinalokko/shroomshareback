package ee.valiit.shroomshareback.controller;

import ee.valiit.shroomshareback.controller.dto.LoginResponse;
import ee.valiit.shroomshareback.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public LoginResponse login(@RequestParam String username, String password){
        return loginService.login(username, password);

    }

}
