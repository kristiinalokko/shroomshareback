package ee.valiit.shroomshareback.persistence.favorite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

  @Query("select f from Favorite f where f.user.id = :userId and f.location.id = :locationId")
  Optional<Favorite> findByUserId(Integer userId, Integer locationId);


}