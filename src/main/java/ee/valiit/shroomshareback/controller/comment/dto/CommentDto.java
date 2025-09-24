package ee.valiit.shroomshareback.controller.comment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ee.valiit.shroomshareback.persistence.comment.Comment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto implements Serializable {
    private Integer locationId;
    private Integer userId;
    @Size(max = 255)
    private String body;
    @NotNull
    private String imageData;
    @NotNull
    private Integer rating;
}