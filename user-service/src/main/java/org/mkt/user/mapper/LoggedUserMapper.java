package org.mkt.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mkt.common.dto.LoginRequest;
import org.mkt.common.dto.LoginResponse;
import org.mkt.common.dto.UserRequest;
import org.mkt.common.dto.UserResponse;
import org.mkt.user.model.LoggedUser;
import org.mkt.user.model.User;

@Mapper
public interface LoggedUserMapper {
    LoggedUserMapper INSTANCE = Mappers.getMapper(LoggedUserMapper.class);

    LoggedUser requestToEntity(LoginRequest request);

    LoginResponse entityToRequest(LoggedUser request);
}
