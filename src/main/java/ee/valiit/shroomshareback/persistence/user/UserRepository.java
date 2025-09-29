package ee.valiit.shroomshareback.persistence.user;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username = :username and u.password = :password and u.status = :code")
    Optional<User> getValidUser(String username, String password, String code);

    @Query("select u from User u where u.username = :username")
    Optional<User> findUserByUsername(String username);

    @Query("select u from User u where u.id = :userId")
    User findUserById(Integer userId);

//    @Override
//    @Query("select u from User u where u.id = :userId")
//    Optional<User> findById(Integer userId);
}