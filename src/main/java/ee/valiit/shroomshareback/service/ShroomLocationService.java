package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.persistence.location.Location;
import ee.valiit.shroomshareback.persistence.location.LocationRepository;
import ee.valiit.shroomshareback.persistence.shroom.Shroom;
import ee.valiit.shroomshareback.persistence.shroom.ShroomRepository;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomLocation;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomlocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShroomLocationService {
    private final LocationRepository locationRepository;
    private final ShroomRepository shroomRepository;
    private final ShroomlocationRepository shroomlocationRepository;

    public void addShroomLocation(Integer locationId, Integer shroomId) {
        Location location = locationRepository.findLocation(locationId);
        Shroom shroom = shroomRepository.findShroom(shroomId);
        ShroomLocation shroomLocation = createAndSetShroomLocation(location, shroom);
        shroomlocationRepository.save(shroomLocation);
    }

    private static ShroomLocation createAndSetShroomLocation(Location location, Shroom shroom) {
        ShroomLocation shroomLocation = new ShroomLocation();
        shroomLocation.setLocation(location);
        shroomLocation.setShroom(shroom);
        return shroomLocation;
    }

    public void deleteShroomLocation(Integer locationId, Integer shroomId) {
        Location location = locationRepository.findLocation(locationId);
        Shroom shroom = shroomRepository.findShroom(shroomId);
        shroomlocationRepository.deleteShroomLocation(location, shroom);
    }
}
