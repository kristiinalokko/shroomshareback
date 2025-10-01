package ee.valiit.shroomshareback.persistence.shroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ShroomRepository extends JpaRepository<Shroom, Integer> {

    @Query("select s from Shroom s where s.id = :shroomId and s.status = :code")
    Optional<Shroom> findShrooms(Integer shroomId, String code);

    @Query("select s from Shroom s where s.id = :shroomId")
    Shroom findShroom(Integer shroomId);

    List<Shroom> getShroomById(Integer id);

    @Transactional
    @Modifying
    @Query("update Shroom s set s.status = :status where s.id = :shroomId")
    void updateStatusBy(String status, Integer shroomId);

}