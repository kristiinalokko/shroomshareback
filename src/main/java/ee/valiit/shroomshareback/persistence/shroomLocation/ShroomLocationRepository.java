package ee.valiit.shroomshareback.persistence.shroomLocation;

import ee.valiit.shroomshareback.persistence.location.Location;
import ee.valiit.shroomshareback.persistence.shroom.Shroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ShroomLocationRepository extends JpaRepository<ShroomLocation, Integer> {
    @Query("select s from ShroomLocation s where s.shroom.id = :shroomId")
    List<ShroomLocation> findShroomLocations(Integer shroomId);

    @Query("select s from ShroomLocation s where s.location.id = :locationId order by s.id")
    List<ShroomLocation> findShroomLocationsBy(Integer locationId);


    @Transactional
    @Modifying
    @Query("delete from ShroomLocation s where s.location = :location and s.shroom = :shroom")
    void deleteShroomLocation(Location location, Shroom shroom);

    @Query("""
        select distinct s.location from ShroomLocation s
        where (:shroomId is null or s.shroom.id = :shroomId)
        and s.location.avgRating >= :minRating
        and s.location.lastActive >= :lastActive
        and s.location.status = 'A'
        group by s.location""")
    List<Location> findFilteredShroomLocationsBy(Integer shroomId, BigDecimal minRating, LocalDate lastActive);
}