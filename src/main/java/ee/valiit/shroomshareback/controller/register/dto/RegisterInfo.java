package ee.valiit.shroomshareback.controller.register.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ee.valiit.shroomshareback.persistence.user.User}
 */
@Value
public class RegisterInfo implements Serializable {
    @NotNull
    @Size(max = 50)
    String username;
    @NotNull
    @Size(max = 50)
    String password;
}