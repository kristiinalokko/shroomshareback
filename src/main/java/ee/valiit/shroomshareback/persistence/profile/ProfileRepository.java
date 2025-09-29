package ee.valiit.shroomshareback.persistence.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    @Query("select p from Profile p where p.user.id = :userId")
    Optional<Profile> findByUserId(Integer userId);


}