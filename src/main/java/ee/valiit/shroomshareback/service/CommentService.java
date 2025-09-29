package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.comment.dto.CommentData;
import ee.valiit.shroomshareback.controller.comment.dto.CommentDto;
import ee.valiit.shroomshareback.infrastructure.exception.DataNotFoundException;
import ee.valiit.shroomshareback.persistence.comment.Comment;
import ee.valiit.shroomshareback.persistence.comment.CommentMapper;
import ee.valiit.shroomshareback.persistence.comment.CommentRepository;
import ee.valiit.shroomshareback.persistence.commentImage.CommentImage;
import ee.valiit.shroomshareback.persistence.commentImage.CommentImageRepository;
import ee.valiit.shroomshareback.persistence.location.Location;
import ee.valiit.shroomshareback.persistence.location.LocationRepository;
import ee.valiit.shroomshareback.persistence.user.User;
import ee.valiit.shroomshareback.persistence.user.UserRepository;
import ee.valiit.shroomshareback.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CommentImageRepository commentImageRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    public List<CommentData> getComments(Integer locationId) {
        List<Comment> comments = commentRepository.findByLocationId(locationId)
                .orElseThrow(() -> new DataNotFoundException(Error.COMMENT_NOT_FOUND.getMessage(), Error.COMMENT_NOT_FOUND.getErrorCode()));

        List<CommentData> commentDatas = createAndSetCommentDatas(comments);

        return commentDatas;
    }

    @Transactional
    public void addComment(CommentDto commentDto) {
        Comment comment = createAndSaveComment(commentDto);

        Integer locationAverageRating = commentRepository.findLocationAverageRating(commentDto.getLocationId());
        locationRepository.updateAvgRatingAndLastActive(locationAverageRating, LocalDate.now(), commentDto.getLocationId());

        if (imageExists(commentDto)) {
            createAndSaveImage(commentDto, comment);
        }
    }

    private static boolean imageExists(CommentDto commentDto) {
        return !commentDto.getImageData().isBlank();
    }

    private Comment createAndSaveComment(CommentDto commentDto) {
        Comment comment = commentMapper.toComment(commentDto);
        addLocationToComment(commentDto, comment);
        addUserToComment(commentDto, comment);
        comment.setCreated(LocalDate.now());
        comment.setStatus(Status.ACTIVE.getCode());
        commentRepository.save(comment);
        return comment;
    }

    private List<CommentData> createAndSetCommentDatas(List<Comment> comments) {
        List<CommentData> commentDatas = new ArrayList<>();

        for (Comment comment : comments) {
            CommentData commentData = (commentMapper.toCommentData(comment));
            Optional<CommentImage> optionalCommentImage = commentImageRepository.findByCommentId(comment.getId());
            handleImage(optionalCommentImage, commentData);
            commentDatas.add(commentData);
        }
        return commentDatas;
    }

    private static void handleImage(Optional<CommentImage> optionalCommentImage, CommentData commentData) {
        if (optionalCommentImage.isPresent()) {
            commentData.setImageData(BytesConverter.bytesToString(optionalCommentImage.get().getImageData()));
        } else {
            commentData.setImageData("");
        }
    }

    private void createAndSaveImage(CommentDto commentDto, Comment comment) {
        CommentImage commentImage = new CommentImage();
        commentImage.setComment(comment);
        commentImage.setImageData(BytesConverter.stringToBytes(commentDto.getImageData()));
        commentImageRepository.save(commentImage);
    }

    private void addUserToComment(CommentDto commentDto, Comment comment) {
        User user = userRepository.findById(commentDto.getUserId()).get();
        comment.setUser(user);
    }

    private void addLocationToComment(CommentDto commentDto, Comment comment) {
        Location location = locationRepository.findById(commentDto.getLocationId()).get();
        comment.setLocation(location);
    }
}
