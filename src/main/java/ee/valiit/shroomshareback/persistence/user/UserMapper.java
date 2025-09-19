package ee.valiit.shroomshareback.persistence.user;

import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.login.dto.LoginResponse;
import ee.valiit.shroomshareback.controller.registration.dto.RegisterInfo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status.class})
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "role.name", target = "roleName")
    LoginResponse toLoginResponse(User user);

    @Mapping(source = "username",target = "username")
    @Mapping(source = "password",target = "password")
    @Mapping(expression = "java(Status.ACTIVE.getCode())",target = "status")
    User toUser(RegisterInfo registerInfo);

}