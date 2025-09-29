package ee.valiit.shroomshareback.persistence.userImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {

  @Query("select u from UserImage u where u.userId = :userId")
  Optional<UserImage> findByUserId(Integer userId);


}