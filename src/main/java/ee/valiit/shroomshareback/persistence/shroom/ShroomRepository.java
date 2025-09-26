package ee.valiit.shroomshareback.persistence.shroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShroomRepository extends JpaRepository<Shroom, Integer> {

    @Query("select s from Shroom s where s.id = :shroomId and s.status = :code")
    Optional<Shroom> findShrooms(Integer shroomId, String code);

    @Query("select s from Shroom s inner join ShroomLocation sl on s.id = sl.shroom.id where sl.location.id = :locationId")
    List<Shroom> findShrooms(Integer locationId);

    @Query("select s from Shroom s where s.id = :shroomId")
    Shroom findShroom(Integer shroomId);
}