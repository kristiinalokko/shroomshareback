package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomDetailedInfo;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomDto;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomInfo;
import ee.valiit.shroomshareback.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.valiit.shroomshareback.persistence.shroom.Shroom;
import ee.valiit.shroomshareback.persistence.shroom.ShroomMapper;
import ee.valiit.shroomshareback.persistence.shroom.ShroomRepository;
import ee.valiit.shroomshareback.persistence.shroomImage.ShroomImage;
import ee.valiit.shroomshareback.persistence.shroomImage.ShroomImageRepository;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomLocationRepository;
import ee.valiit.shroomshareback.persistence.user.User;
import ee.valiit.shroomshareback.persistence.user.UserRepository;
import ee.valiit.shroomshareback.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class ShroomService {
    private final ShroomRepository shroomRepository;
    private final ShroomMapper shroomMapper;
    private final ShroomImageRepository shroomImageRepository;
    private final ShroomLocationRepository shroomLocationRepository;
    private final UserRepository userRepository;

    public ShroomDetailedInfo getShroomDetailedInfo(Integer shroomId) {
        Shroom shroom = getValidShroom(shroomId);
        return shroomMapper.toShroomDetailedInfo(shroom);
    }

    private Shroom getValidShroom(Integer shroomId) {
        return shroomRepository.findById(shroomId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("shroomId", shroomId));
    }

    public List<ShroomInfo> getShrooms() {
        List<Shroom> shrooms = shroomRepository.findAll();
        return shroomMapper.toShroomInfos(shrooms);

    }

    public void addShroom(Integer userId, ShroomDto shroomDto) {
        Shroom shroom = createAndSaveShroom(userId, shroomDto);
        handleAddShroomImage(shroomDto, shroom);
    }

    private Shroom createAndSaveShroom(Integer userId, ShroomDto shroomDto) {
        User user = getValidUser(userId);
        Shroom shroom = createShroom(user, shroomDto);
        shroomRepository.save(shroom);
        return shroom;
    }

    private Shroom createShroom(User user, ShroomDto shroomDto) {
        Shroom shroom = shroomMapper.toShroom(shroomDto);
        shroom.setUser(user);
        return shroom;
    }

    private void handleAddShroomImage(ShroomDto shroomDto, Shroom shroom) {
        if (shroomHasImage(shroomDto)) {
            createAndSaveShroomImage(shroomDto, shroom);
        }
    }

    private void handleUpdateShroomImage(ShroomDto shroomDto, Shroom shroom) {
        if (shroomHasImage(shroomDto)) {
            Optional<ShroomImage> optionalShroomImage = shroomImageRepository.findShroomImageBy(shroom);
            if (optionalShroomImage.isPresent()) {
                ShroomImage shroomImage = optionalShroomImage.get();
                shroomImage.setImageData(BytesConverter.stringToBytes(shroomDto.getShroomImage()));
                shroomImageRepository.save(shroomImage);
            } else {
                createAndSaveShroomImage(shroomDto, shroom);
            }
        }
    }

    private void createAndSaveShroomImage(ShroomDto shroomDto, Shroom shroom) {
        ShroomImage shroomImage = createShroomImage(shroomDto, shroom);
        shroomImageRepository.save(shroomImage);
    }

    private static ShroomImage createShroomImage(ShroomDto shroomDto, Shroom shroom) {
        byte[] imageData = BytesConverter.stringToBytes(shroomDto.getShroomImage());
        ShroomImage shroomImage = new ShroomImage();
        shroomImage.setShroom(shroom);
        shroomImage.setImageData(imageData);
        return shroomImage;
    }

    private static boolean shroomHasImage(ShroomDto shroomDto) {
        return !shroomDto.getShroomImage().isEmpty();
    }

    private User getValidUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new PrimaryKeyNotFoundException("userId", userId));
    }

    public void updateShroom(Integer shroomId, ShroomDto shroomDto) {
        Shroom shroom = getValidShroom(shroomId);
        shroomMapper.updateShroom(shroomDto, shroom);
        handleUpdateShroomImage(shroomDto, shroom);
    }

    public List<ShroomDetailedInfo> getAllShroomsDetailedInfo() {
        List<Shroom> shrooms = shroomRepository.findAll();
        return shroomMapper.toShroomDetailedInfos(shrooms);
    }

    public void deleteShroom(Integer shroomId) {
        shroomRepository.updateStatusBy(Status.DEACTIVATED.getCode(), shroomId);
    }
}
