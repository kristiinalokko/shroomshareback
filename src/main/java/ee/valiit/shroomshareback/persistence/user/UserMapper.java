package ee.valiit.shroomshareback.persistence.user;


import ee.valiit.shroomshareback.Status;
import ee.valiit.shroomshareback.controller.login.dto.LoginResponse;
import ee.valiit.shroomshareback.controller.register.dto.RegisterInfo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status.class})
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "role.name", target = "roleName")
    LoginResponse toLoginResponse(User user);

    @Mapping(expression = "java(Status.ACTIVE.getCode())", target = "status")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "username", target = "username")
    User toUser(RegisterInfo registerInfo);

}