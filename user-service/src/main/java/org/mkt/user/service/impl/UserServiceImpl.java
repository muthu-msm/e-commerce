package org.mkt.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.mkt.common.dto.Response;
import org.mkt.common.dto.UserRequest;
import org.mkt.common.dto.UserResponse;
import org.mkt.user.mapper.UserMapper;
import org.mkt.user.model.User;
import org.mkt.user.repository.UserRepository;
import org.mkt.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    @Override
    public Response<UserResponse> create(UserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.save(UserMapper.INSTANCE.requestToEntity(request));

        return new Response<>(1, UserMapper.INSTANCE.entityToRequest(user));
    }

    @Override
    public Response<UserResponse> update(UserRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getId());

        return optionalUser.map(value -> {
                    request.setPassword(optionalUser.get().getPassword());
                    return new Response<>(1, UserMapper.INSTANCE.entityToRequest(userRepository.save(UserMapper.INSTANCE.requestToEntity(request))));
                })
                .orElseGet(() -> new Response<>(404, "Not found"));
    }

    @Override
    public Response<Long> delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.map(value -> {
                    optionalUser.get().setStatus("D");
                    userRepository.save(optionalUser.get());
                    return new Response<>(1, id);
                })
                .orElseGet(() -> new Response<>(404, "Not found"));
    }

    @Override
    public Response<UserResponse> get(Long id) {
        Optional<User> order = userRepository.findById(id);

        return order.map(value -> new Response<>(1, UserMapper.INSTANCE.entityToRequest(value)))
                .orElseThrow(() -> new Response<>(404, "Not found", null));

    }
}
