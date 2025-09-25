package ee.valiit.shroomshareback.persistence.shroom;

import ee.valiit.shroomshareback.controller.shroom.dto.ShroomInfo;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomProfile;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShroomMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "name", target = "name")
    ShroomInfo toShroomInfo(Shroom shroom);


    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Shroom shroomProfileToShroom(ShroomProfile shroomProfile);
}