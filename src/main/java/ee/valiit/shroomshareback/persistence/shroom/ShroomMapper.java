package ee.valiit.shroomshareback.persistence.shroom;

import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomDetailedInfo;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomDto;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status.class})
public interface ShroomMapper {


    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(expression = "java(Status.PENDING.getCode())", target = "status")
    Shroom toShroom(ShroomDto shroomDto);

    @InheritConfiguration(name = "toShroom")
    Shroom updateShroom(ShroomDto shroomDto, @MappingTarget Shroom shroom);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "id", target = "shroomId")
    @Mapping(source = "name", target = "shroomName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    ShroomDetailedInfo toShroomDetailedInfo(Shroom shroom);

    List<ShroomDetailedInfo> toShroomDetailedInfos(List<Shroom> shrooms);


    @Mapping(source = "id", target = "shroomId")
    @Mapping(source = "name", target = "shroomName")
    ShroomInfo toShroomInfo(Shroom shroom);

   List <ShroomInfo> toShroomInfos(List <Shroom> shrooms);

}