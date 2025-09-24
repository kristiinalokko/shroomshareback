package ee.valiit.shroomshareback.persistence.locationImge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocationImageRepository extends JpaRepository<LocationImage, Integer> {

    @Query("select l from LocationImage l where l.location.id = :id")
    Optional<LocationImage> findImageByLocationId(Integer id);

}