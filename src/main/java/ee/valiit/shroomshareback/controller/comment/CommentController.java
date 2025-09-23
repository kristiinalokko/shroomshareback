package ee.valiit.shroomshareback.controller.comment;

import ee.valiit.shroomshareback.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment")
    public List<CommentData> getComments(@RequestParam Integer locationId) {
        return commentService.getComments(locationId);
    }
}
