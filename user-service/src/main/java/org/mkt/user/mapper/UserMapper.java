package org.mkt.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mkt.common.dto.UserRequest;
import org.mkt.common.dto.UserResponse;
import org.mkt.user.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User requestToEntity(UserRequest request);

    UserResponse entityToRequest(User request);
}
