package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.login.dto.LoginResponse;
import ee.valiit.shroomshareback.infrastructure.exception.DataNotFoundException;
import ee.valiit.shroomshareback.persistence.user.User;
import ee.valiit.shroomshareback.persistence.user.UserMapper;
import ee.valiit.shroomshareback.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public LoginResponse login(String username, String password) {
        User validUser = userRepository.getValidUser(username, password, Status.ACTIVE.getCode())
                .orElseThrow(() -> new DataNotFoundException(Error.INCORRECT_CREDENTIALS.getMessage(),Error.INCORRECT_CREDENTIALS.getErrorCode()));

        return userMapper.toLoginResponse(validUser);

    }
}
