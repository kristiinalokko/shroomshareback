package ee.valiit.shroomshareback.persistence.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status.class})
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
    @Mapping(expression = "java(java.time.LocalDate.now())", target = "lastActive")
    Location dtoToLocation(LocationDto locationDto);

    @Mapping(source = "locationName", target = "name")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "description", target = "description")
    @Mapping(expression = "java(java.time.LocalDate.now())", target = "lastActive")
    void updateLocationFromDto(@MappingTarget Location location, LocationDto locationDto);
}
