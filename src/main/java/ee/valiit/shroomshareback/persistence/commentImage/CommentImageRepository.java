package ee.valiit.shroomshareback.persistence.commentImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentImageRepository extends JpaRepository<CommentImage, Integer> {

    @Query("select c from CommentImage c where c.comment.id = :commentId")
    Optional<CommentImage> findByCommentId(Integer commentId);
}