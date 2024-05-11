package org.mkt.user.service;

import org.mkt.common.dto.Response;
import org.mkt.common.dto.UserRequest;
import org.mkt.common.dto.UserResponse;

public interface UserService extends IBaseService<UserRequest, Response<UserResponse>>{
}
