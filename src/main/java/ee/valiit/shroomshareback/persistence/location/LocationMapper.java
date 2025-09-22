package ee.valiit.shroomshareback.persistence.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

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
//    @Mapping(source = "", target = "isFavourite")
    LocationInfo toLocationInfo(Location location);

}
