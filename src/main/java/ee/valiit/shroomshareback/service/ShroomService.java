package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Error;
import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.location.dto.SimplifiedLocation;
import ee.valiit.shroomshareback.controller.shroom.dto.*;
import ee.valiit.shroomshareback.infrastructure.exception.DataNotFoundException;
import ee.valiit.shroomshareback.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.valiit.shroomshareback.persistence.shroom.Shroom;
import ee.valiit.shroomshareback.persistence.shroom.ShroomMapper;
import ee.valiit.shroomshareback.persistence.shroom.ShroomRepository;
import ee.valiit.shroomshareback.persistence.shroomImage.ShroomImage;
import ee.valiit.shroomshareback.persistence.shroomImage.ShroomImageRepository;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomLocation;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomlocationRepository;
import ee.valiit.shroomshareback.persistence.user.User;
import ee.valiit.shroomshareback.persistence.user.UserRepository;
import ee.valiit.shroomshareback.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ee.valiit.shroomshareback.util.ShroomUtil.createAndSaveShroomBasicInfos;

@Service
@RequiredArgsConstructor

public class ShroomService {
    private final ShroomRepository shroomRepository;
    private final ShroomMapper shroomMapper;
    private final ShroomImageRepository shroomImageRepository;
    private final ShroomlocationRepository shroomLocationRepository;
    private final UserRepository userRepository;

    public ShroomInfo getShroom(Integer shroomId) {
        Shroom shroom = getValidShroom(shroomId);
        ShroomInfo shroomInfo = shroomMapper.toShroomInfo(shroom);
        setShroomImage(shroomInfo, shroomId);
        setShroomLocations(shroomInfo, shroomId);
        return shroomInfo;
    }

    private Shroom getValidShroom(Integer shroomId) {
        return shroomRepository.findShrooms(shroomId, Status.ACTIVE.getCode())
                .orElseThrow(() -> new DataNotFoundException(Error.SHROOM_NOT_FOUND.getMessage(), Error.SHROOM_NOT_FOUND.getErrorCode()));
    }

    private void setShroomImage(ShroomInfo shroomInfo, Integer shroomId){
        Optional<ShroomImage> optionalShroomImage = shroomImageRepository.findShroomImage(shroomId);
        if(optionalShroomImage.isPresent()){
            String shroomImage = BytesConverter.bytesToString(optionalShroomImage.get().getImageData());
            shroomInfo.setShroomImage(shroomImage);
        } else {
            shroomInfo.setShroomImage("");
        }
    }
    private void setShroomLocations(ShroomInfo shroomInfo, Integer shroomId) {
        List<ShroomLocation> shroomLocations = shroomLocationRepository.findShroomLocations(shroomId);
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

    public List<ShroomBasicInfo> getShrooms() {
        List<Shroom> shrooms = shroomRepository.findAll();
        return createAndSaveShroomBasicInfos(shrooms);
    }

    public void addShroom(Integer userId, ShroomProfile shroomProfile) {
        Shroom shroom = createAndSaveShroom(userId, shroomProfile);
        handleShroomImage(shroomProfile, shroom);
    }

    private Shroom createAndSaveShroom(Integer userId, ShroomProfile shroomProfile) {
        User user = getValidUser(userId);
        Shroom shroom = createShroom(user, shroomProfile);
        shroomRepository.save(shroom);
        return shroom;
    }

    private Shroom createShroom(User user, ShroomProfile shroomProfile) {
        Shroom shroom = shroomMapper.shroomProfileToShroom(shroomProfile);
        shroom.setUser(user);
        return shroom;
    }

    private void handleShroomImage(ShroomProfile shroomProfile, Shroom shroom) {
        if (shroomHasImage(shroomProfile)){
            createAndSaveShroomImage(shroomProfile, shroom);
        }
    }

    private void createAndSaveShroomImage(ShroomProfile shroomProfile, Shroom shroom) {
        ShroomImage shroomImage = createShroomImage(shroomProfile, shroom);
        shroomImageRepository.save(shroomImage);
    }

    private static ShroomImage createShroomImage(ShroomProfile shroomProfile, Shroom shroom) {
        byte[] imageData = BytesConverter.stringToBytes(shroomProfile.getShroomImage());
        ShroomImage shroomImage = new ShroomImage();
        shroomImage.setShroom(shroom);
        shroomImage.setImageData(imageData);
        return shroomImage;
    }

    private static boolean shroomHasImage(ShroomProfile shroomProfile) {
        return !shroomProfile.getShroomImage().isEmpty();
    }

    private User getValidUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new PrimaryKeyNotFoundException("userId", userId));
    }

    public void editShroom(Integer shroomId, ShroomProfile shroomProfile) {
        Shroom shroom = getValidShroom(shroomId);
        shroomMapper.updateShroomFromShroomProfile(shroomProfile, shroom);
        handleShroomImage(shroomProfile, shroom);
    }




    public List<ShroomWithUsername> getAllShrooms() {
        List<Shroom> shrooms = shroomRepository.findAll();
        return shroomMapper.toShroomWithUsernames(shrooms);
    }
}
