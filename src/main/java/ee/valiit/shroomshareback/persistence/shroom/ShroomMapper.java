package ee.valiit.shroomshareback.persistence.shroom;

import ee.valiit.shroomshareback.controller.shroom.ShroomInfo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShroomMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "description", target = "shroomDescription")
    @Mapping(source = "name", target = "shroomName")
    ShroomInfo toShroomInfo(Shroom shroom);

}