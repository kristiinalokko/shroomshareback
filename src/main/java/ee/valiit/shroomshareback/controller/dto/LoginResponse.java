package ee.valiit.shroomshareback.controller.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ee.valiit.shroomshareback.persistence.user.User}
 */
@Value
public class LoginResponse implements Serializable {
    Integer userId;
    String roleName;
}