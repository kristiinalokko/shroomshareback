package ee.valiit.shroomshareback.persistence.comment;

import ee.valiit.shroomshareback.controller.comment.CommentData;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "body", target = "body")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "created", target = "created")
    CommentData toCommentData(Comment comment);

}