package ee.valiit.shroomshareback.persistence.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("select c from Comment c where c.location.id = :id")
    Optional<List<Comment>> findByLocationId(Integer id);

    @Query("select avg(c.rating) as avg_rating from Comment c where c.location.id = :locationId")
    Integer findLocationAverageRating(Integer locationId);


}