package ee.valiit.shroomshareback.controller.registration;

import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.controller.registration.dto.RegisterInfo;
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

    private static final String ROLENAME = "user";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public void addUser(RegisterInfo registerInfo) {
        String username = registerInfo.getUsername();
        Optional<User> optionalUsername = userRepository.findUserByUsername(username);
        if (optionalUsername.isEmpty()){
            Role role = roleRepository.getRoleByRoleName(ROLENAME);
            User user = userMapper.toUser(registerInfo);
            user.setRole(role);
            userRepository.save(user);
        } else {
            throw new ForbiddenException(Error.INCORRECT_USERNAME.getMessage(), Error.INCORRECT_USERNAME.getErrorCode());
        }
    }
}
