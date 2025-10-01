package ee.valiit.shroomshareback.persistence.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Override
    @Query("select l from Location l where l.id = :locationId")
    Optional<Location> findById(Integer locationId);

    @Modifying
    @Query("update Location l set l.avgRating = :locationAverageRating, l.lastActive = :date where l.id = :locationId")
    void updateAvgRatingAndLastActive(Integer locationAverageRating, LocalDate date, Integer locationId);

    @Query("select l from Location l where l.id = :locationId")
    Location findLocation(Integer locationId);

    @Transactional
    @Modifying
    @Query("update Location l set l.status=:code where l.id = :locationId")
    void updateStatusBy(String code, Integer locationId);

}