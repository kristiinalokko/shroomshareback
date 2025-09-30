package ee.valiit.shroomshareback.persistence.shroomLocation;

import ee.valiit.shroomshareback.controller.location.dto.LocationMapInfo;
import ee.valiit.shroomshareback.controller.shroom.dto.ShroomInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShroomLocationMapper {

    @Mapping(source = "shroom.id", target = "shroomId")
    @Mapping(source = "shroom.name", target = "shroomName")
    ShroomInfo toShroomLocationInfo(ShroomLocation shroomLocation);

    List<ShroomInfo> toShroomLocationInfos(List<ShroomLocation> shroomLocations);

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.name", target = "locationName")
    @Mapping(source = "location.latitude", target = "latitude")
    @Mapping(source = "location.longitude", target = "longitude")
    @Mapping(source = "location.user.username", target = "username")
    @Mapping(source = "location.created", target = "createdAt")
    @Mapping(source = "location.avgRating", target = "avgRating")
    LocationMapInfo toLocationMapInfo(ShroomLocation shroomLocation);

    List<LocationMapInfo> toLocationMapInfos(List<ShroomLocation> shroomLocations);
}