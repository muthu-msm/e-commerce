package org.mkt.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.mkt.common.dto.Response;
import org.mkt.common.dto.UserRequest;
import org.mkt.common.dto.UserResponse;
import org.mkt.user.mapper.UserMapper;
import org.mkt.user.model.User;
import org.mkt.user.repository.UserRepository;
import org.mkt.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public Response<UserResponse> create(UserRequest request) {
        request.setOrderID(null);
        User user = userRepository.save(UserMapper.INSTANCE.requestToEntity(request));

        return new Response<>(1, UserMapper.INSTANCE.entityToRequest(user));
    }

    @Override
    public Response<UserResponse> update(UserRequest request) {
        User user = userRepository.save(UserMapper.INSTANCE.requestToEntity(request));
        return new Response<>(1, UserMapper.INSTANCE.entityToRequest(user));
    }

    @Override
    public Long delete(Long id) {
        return 0L;
    }

    @Override
    public Response<UserResponse> get(Long id) {
        Optional<User> order = userRepository.findById(id);

        return order.map(value -> new Response<>(1, UserMapper.INSTANCE.entityToRequest(value)))
                .orElseGet(() -> new Response<>(404, "Not found", null));

    }
}
