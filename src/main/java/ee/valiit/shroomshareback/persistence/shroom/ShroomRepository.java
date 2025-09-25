package ee.valiit.shroomshareback.persistence.shroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShroomRepository extends JpaRepository<Shroom, Integer> {

    @Query("select s from Shroom s where s.id = :id and s.status = :code")
    Optional<Shroom> findShroomById(Integer id, String code);

    @Query("select s from Shroom s inner join s.locations locations where locations.id = :locationId")
    List<Shroom> findShroomByLocationId(Integer locationId);
}