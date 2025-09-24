package ee.valiit.shroomshareback.persistence.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("select l from Location l where l.id = :locationId and l.status = :status")
    Optional<Location> findByIdAndStatus(@Param("id") Integer locationId, @Param("status") String status);


}