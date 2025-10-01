package ee.valiit.shroomshareback.persistence.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import ee.valiit.shroomshareback.controller.location.dto.LocationMapInfo;
import ee.valiit.shroomshareback.controller.location.dto.LocationExtendedInfo;
import org.mapstruct.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status.class})
public interface LocationMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "created", target = "createdAt")
    @Mapping(source = "avgRating", target = "avgRating")
    LocationInfo toLocationInfo(Location location);

    @Mapping(source = "locationName", target = "name")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "description", target = "description")
    @Mapping(expression = "java(java.time.LocalDate.now())", target = "lastActive")
    Location toLocation(LocationDto locationDto);

    @Mapping(source = "locationName", target = "name")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "description", target = "description")
    @Mapping(expression = "java(java.time.LocalDate.now())", target = "lastActive")
    void updateLocation(@MappingTarget Location location, LocationDto locationDto);


    @Mapping(source = "id", target = "locationId")
    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "created", target = "createdAt")
    @Mapping(source = "avgRating", target = "avgRating")
    LocationMapInfo toLocationMapInfo(Location location);

    List<LocationMapInfo> toLocationMapInfos(List<Location> locations);

    @Mapping(source = "id", target = "locationId")
    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    LocationExtendedInfo toLocationExtendedInfo(Location location);

    List<LocationExtendedInfo> toLocationExtendedInfos(List<Location> locations);
}
