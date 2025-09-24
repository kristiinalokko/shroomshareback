package ee.valiit.shroomshareback.controller.comment;

import ee.valiit.shroomshareback.controller.comment.dto.CommentData;
import ee.valiit.shroomshareback.controller.comment.dto.CommentDto;
import ee.valiit.shroomshareback.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment")
    public List<CommentData> getComments(@RequestParam Integer locationId) {
        return commentService.getComments(locationId);
    }

    @PostMapping("/comment")
    public void addComment(@RequestBody CommentDto commentDto) {
        commentService.addComment(commentDto);
    }
}
