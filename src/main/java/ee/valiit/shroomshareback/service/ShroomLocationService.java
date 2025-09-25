package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.persistence.location.Location;
import ee.valiit.shroomshareback.persistence.location.LocationRepository;
import ee.valiit.shroomshareback.persistence.shroom.ShroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShroomLocationService {
    private final LocationRepository locationRepository;
    private final ShroomRepository shroomRepository;

    public void addShroomLocation(Integer locationId, Integer shroomId) {
        Location location = locationRepository.findLocation(locationId);
        shroomRepository.findShroom(shroomId);
    }
}
