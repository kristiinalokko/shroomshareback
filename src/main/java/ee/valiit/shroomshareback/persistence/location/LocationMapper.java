package ee.valiit.shroomshareback.persistence.location;

import ee.valiit.shroomshareback.controller.location.dto.LocationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "description", target = "description")
    LocationDto toLocationExtendedInfo(Location location);

}