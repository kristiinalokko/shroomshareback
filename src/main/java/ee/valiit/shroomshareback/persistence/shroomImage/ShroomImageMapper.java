package ee.valiit.shroomshareback.persistence.shroomImage;

import ee.valiit.shroomshareback.controller.shroomimage.dto.ShroomImageDto;
import ee.valiit.shroomshareback.util.BytesConverter;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {BytesConverter.class})
public interface ShroomImageMapper {

    @Mapping(source = "imageData", target = "imageData", qualifiedByName = "bytesToString")
    ShroomImageDto toShroomImageDto(ShroomImage shroomImage);

    @Named("bytesToString")
    static String bytesToString(byte[] imageData) {
        return BytesConverter.bytesToString(imageData);
    }
}