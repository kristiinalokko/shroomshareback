package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.location.dto.SimplifiedLocation;
import ee.valiit.shroomshareback.controller.shroom.ShroomInfo;
import ee.valiit.shroomshareback.infrastructure.exception.DataNotFoundException;
import ee.valiit.shroomshareback.persistence.shroom.Shroom;
import ee.valiit.shroomshareback.persistence.shroom.ShroomMapper;
import ee.valiit.shroomshareback.persistence.shroom.ShroomRepository;
import ee.valiit.shroomshareback.persistence.shroomImage.ShroomImage;
import ee.valiit.shroomshareback.persistence.shroomImage.ShroomImageRepository;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomLocation;
import ee.valiit.shroomshareback.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ShroomService {
    private final ShroomRepository shroomRepository;
    private final ShroomMapper shroomMapper;
    private final ShroomImageRepository shroomImageRepository;
    private final ShroomlocationRepository shroomLocationRepository;

    public ShroomInfo getShroom(Integer shroomId) {
        Shroom shroom = findShroomById(shroomId);
        ShroomInfo shroomInfo = shroomMapper.toShroomInfo(shroom);
        setShroomImage(shroomInfo, shroomId);
        setShroomLocations(shroomInfo, shroomId);
        return shroomInfo;
    }

    private Shroom findShroomById(Integer shroomId) {
        Optional<Shroom> optionalShroom = shroomRepository.findShroomById(shroomId, Status.ACTIVE.getCode());
        if (optionalShroom.isEmpty()) {
            throw new DataNotFoundException(Error.SHROOM_NOT_FOUND.getMessage(), Error.SHROOM_NOT_FOUND.getErrorCode());
        }
        return optionalShroom.get();
    }

    private void setShroomImage(ShroomInfo shroomInfo, Integer shroomId){
        Optional<ShroomImage> optionalShroomImage = shroomImageRepository.findShroomImageById(shroomId);
        if(optionalShroomImage.isPresent()){
            String shroomImage = BytesConverter.bytesToString(optionalShroomImage.get().getImageData());
            shroomInfo.setShroomImage(shroomImage);
        }
    }
    private void setShroomLocations(ShroomInfo shroomInfo, Integer shroomId) {
        List<ShroomLocation> shroomLocations = shroomLocationRepository.findShroomLocationByShroom_Id(shroomId);
        List<SimplifiedLocation> locations = mapToSimplifiedLocations(shroomLocations);
        shroomInfo.setLocations(locations);
    }

    private List<SimplifiedLocation> mapToSimplifiedLocations(List<ShroomLocation> shroomLocations) {
        List<SimplifiedLocation> locations = new ArrayList<>();
        for (ShroomLocation shroomLocation : shroomLocations) {
            SimplifiedLocation simplifiedLocation = new SimplifiedLocation();
            simplifiedLocation.setLocationId(shroomLocation.getLocation().getId());
            simplifiedLocation.setLocationName(shroomLocation.getLocation().getName());
            locations.add(simplifiedLocation);
        }
        return locations;
    }
}
