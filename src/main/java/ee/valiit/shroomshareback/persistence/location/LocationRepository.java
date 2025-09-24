package ee.valiit.shroomshareback.persistence.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("SELECT DISTINCT l FROM Location l " +
            "LEFT JOIN l.shrooms s " +
            "WHERE (:shroomName IS NULL OR LOWER(s.name) LIKE LOWER(CAST(CONCAT('%', :shroomName, '%') as text))) " +
            "AND (:rating IS NULL OR l.avgRating >= :rating) " +
            "AND (:lastActiveAfter IS NULL OR l.lastActive >= :lastActiveAfter) " +
            "AND (:lat IS NULL OR :lon IS NULL OR :radius IS NULL OR " +
            "6371 * acos(cos(radians(:lat)) * cos(radians(l.latitude)) * " +
            "cos(radians(l.longitude) - radians(:lon)) + sin(radians(:lat)) * " +
            "sin(radians(l.latitude))) <= :radius)")
    List<Location> findFilteredLocations(@Param("shroomName") String shroomName,
                                         @Param("rating") BigDecimal rating,
                                         @Param("lastActiveAfter") LocalDate lastActiveAfter,
                                         @Param("lat") BigDecimal lat,
                                         @Param("lon") BigDecimal lon,
                                         @Param("radius") BigDecimal radius);
}