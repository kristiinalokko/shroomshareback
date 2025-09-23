package ee.valiit.shroomshareback.persistence.favorite;

import ee.valiit.shroomshareback.persistence.location.Location;
import ee.valiit.shroomshareback.persistence.user.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

  @Query("select f from Favorite f where f.user.id = :userId and f.location.id = :locationId")
  Optional<Favorite> findByLocationIdAndUserId(Integer locationId, Integer userId);

  @Transactional
  @Modifying
  @Query("delete from Favorite f where f.user = :user and f.location = :location")
  void deleteFavorite(User user, Location location);

}