package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomLocation;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ShroomlocationRepository extends Repository<ShroomLocation, Integer> {
    List<ShroomLocation> findShroomLocationByShroom_Id(Integer shroomId);
}