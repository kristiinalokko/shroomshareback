package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.controller.register.dto.RegisterInfo;
import ee.valiit.shroomshareback.infrastructure.exception.ForbiddenException;
import ee.valiit.shroomshareback.persistence.role.Role;
import ee.valiit.shroomshareback.persistence.role.RoleRepository;
import ee.valiit.shroomshareback.persistence.user.User;
import ee.valiit.shroomshareback.persistence.user.UserMapper;
import ee.valiit.shroomshareback.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    public static final String ROLE_NAME_USER = "user";


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public void addUser(RegisterInfo registerInfo) {
        String username = registerInfo.getUsername();
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isEmpty()) {
            User user = userMapper.toUser(registerInfo);
            Role role = roleRepository.getRoleByRoleName(ROLE_NAME_USER);
            user.setRole(role);
            userRepository.save(user);
        } else {
            throw new ForbiddenException(Error.INCORRECT_USERNAME.getMessage(), Error.INCORRECT_USERNAME.getErrorCode());
        }

    }
}
