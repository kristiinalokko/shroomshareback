package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.controller.comment.CommentData;
import ee.valiit.shroomshareback.infrastructure.exception.DataNotFoundException;
import ee.valiit.shroomshareback.persistence.comment.Comment;
import ee.valiit.shroomshareback.persistence.comment.CommentMapper;
import ee.valiit.shroomshareback.persistence.comment.CommentRepository;
import ee.valiit.shroomshareback.persistence.commentImage.CommentImage;
import ee.valiit.shroomshareback.persistence.commentImage.CommentImageRepository;
import ee.valiit.shroomshareback.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CommentImageRepository commentImageRepository;

    public List<CommentData> getComments(Integer locationId) {
        Optional<List<Comment>> optionalComments = commentRepository.findByLocationId(locationId);

        if (optionalComments.isPresent()) {

            List<Comment> comments = optionalComments.get();
            List<CommentData> commentDatas = new ArrayList<>();

            for (Comment comment : comments) {
                CommentData commentData = (commentMapper.toCommentData(comment));
                Optional<CommentImage> optionalCommentImage = commentImageRepository.findByCommentId(comment.getId());
                if (optionalCommentImage.isPresent()) {
                    commentData.setImageData(BytesConverter.bytesToString(optionalCommentImage.get().getImageData()));
                } else {
                    commentData.setImageData("");
                }
                commentDatas.add(commentData);
            }

            return commentDatas;
        } else {
            throw new DataNotFoundException(Error.COMMENT_NOT_FOUND.getMessage(), Error.COMMENT_NOT_FOUND.getErrorCode());
        }

    }


}
