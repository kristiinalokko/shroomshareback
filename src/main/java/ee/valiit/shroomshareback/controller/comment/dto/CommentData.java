package ee.valiit.shroomshareback.controller.comment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ee.valiit.shroomshareback.persistence.comment.Comment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentData implements Serializable {
    private String username;
    @Size(max = 255)
    private String body;
    @NotNull
    private Integer rating;
    @NotNull
    private LocalDate created;
    @NotNull
    private String imageData;
}