package ee.valiit.shroomshareback.persistence.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import ee.valiit.shroomshareback.controller.location.dto.LocationShortInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "description", target = "description")
//    @Mapping(source = "", target = "locationImage")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "created", target = "createdAt")
    @Mapping(source = "avgRating", target = "avgRating")
    LocationInfo toLocationInfo(Location location);

    @Mapping(source = "locationName", target = "name")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "description", target = "description")
    @Mapping(expression = "java(java.time.LocalDate.now())", target = "created")
    Location dtoToLocation(LocationDto locationDto);


    @Mapping(source = "id", target = "locationId")
    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "created", target = "createdAt")
    @Mapping(source = "avgRating", target = "avgRating")
    LocationShortInfo toLocationShortInfo(Location location);

    List<LocationShortInfo> toLocationShortInfos(List<Location> locations);


}
