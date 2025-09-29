package ee.valiit.shroomshareback.persistence.shroom;

import ee.valiit.shroomshareback.controller.shroom.dto.ShroomDto;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomInfo;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomProfile;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomWithUsername;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShroomMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "name", target = "name")
    ShroomInfo toShroomInfo(Shroom shroom);


    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Shroom shroomProfileToShroom(ShroomProfile shroomProfile);

    @Mapping(source = "id", target = "shroomId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "status", target = "status")
    ShroomWithUsername toShroomWithUsername(Shroom shroom);

    List<ShroomWithUsername> toShroomWithUsernames(List<Shroom> shrooms);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Shroom updateShroomFromShroomProfile(ShroomProfile shroomProfile,@MappingTarget Shroom shroom);
}