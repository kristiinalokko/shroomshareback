package ee.valiit.shroomshareback.persistence.shroomLocation;

import ee.valiit.shroomshareback.persistence.location.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ShroomlocationRepository extends Repository<ShroomLocation, Integer> {

    @Query("select s from ShroomLocation s where s.shroom.id = :shroomId")
    List<ShroomLocation> findShroomLocationsBy(Integer shroomId);

    @Query("""
            select distinct s.location from ShroomLocation s
            where s.shroom.id = :shroomId and s.location.avgRating >= :minRating and s.location.lastActive >= :lastActive group by s.location""")
    List<Location> findFilteredShroomLocationsBy(Integer shroomId, BigDecimal minRating, LocalDate lastActive);


}