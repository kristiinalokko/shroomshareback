package ee.valiit.shroomshareback.persistence.comment;

import ee.valiit.shroomshareback.controller.comment.dto.CommentData;
import ee.valiit.shroomshareback.controller.comment.dto.CommentDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    @Mapping(source = "body", target = "body")
    @Mapping(source = "rating", target = "rating")
    Comment toComment(CommentDto commentDto);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "body", target = "body")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "created", target = "created")
    CommentData toCommentData(Comment comment);

}