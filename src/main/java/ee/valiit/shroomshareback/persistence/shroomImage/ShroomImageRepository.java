package ee.valiit.shroomshareback.persistence.shroomImage;

import ee.valiit.shroomshareback.persistence.shroom.Shroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShroomImageRepository extends JpaRepository<ShroomImage, Integer> {

    @Query("select s from ShroomImage s where s.shroom.id = :shroomId")
    Optional<ShroomImage> findShroomImageBy(Integer shroomId);

    @Query("select s from ShroomImage s where s.shroom = :shroom")
    Optional<ShroomImage> findShroomImageBy( Shroom shroom);


}