package ee.valiit.shroomshareback.persistence.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("select l from Location l where l.id = :locationId and l.status = :status")
    Optional<Location> findByIdAndStatus(Integer locationId, String status);

    @Query("UPDATE Location l SET l.avgRating = :locationAverageRating, l.lastActive = :date WHERE l.id = :locationId")
    void updateAvgRatingAndLastActive(Integer locationAverageRating, LocalDate date, Integer locationId);
}