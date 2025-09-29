package ee.valiit.shroomshareback.controller.profile.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ee.valiit.shroomshareback.persistence.profile.Profile}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileData implements Serializable {
    private Integer profileId;
    private Integer userId;
    private String username;
    private String password;
    private String userStatus;
    @NotNull
    @Size(max = 255)
    private String firstName;
    @NotNull
    @Size(max = 255)
    private String lastName;
    @NotNull
    @Size(max = 255)
    private String email;
    @Size(max = 255)
    private String description;
    private String imageData;
}