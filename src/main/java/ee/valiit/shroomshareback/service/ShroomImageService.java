package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.valiit.shroomshareback.persistence.shroomImage.ShroomImage;
import ee.valiit.shroomshareback.controller.shroomimage.dto.ShroomImageDto;
import ee.valiit.shroomshareback.persistence.shroomImage.ShroomImageMapper;
import ee.valiit.shroomshareback.persistence.shroomImage.ShroomImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShroomImageService {

    private final ShroomImageRepository shroomImageRepository;
    private final ShroomImageMapper shroomImageMapper;

    public ShroomImageDto getShroomImage(Integer shroomId) {
        ShroomImage shroomImage = shroomImageRepository.findById(shroomId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("shroomId", shroomId));
        return shroomImageMapper.toShroomImageDto(shroomImage);
    }
}
