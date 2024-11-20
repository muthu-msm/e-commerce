package org.mkt.user.service;

import lombok.RequiredArgsConstructor;
import org.mkt.common.dto.LoginRequest;
import org.mkt.common.dto.LoginResponse;
import org.mkt.common.dto.Response;
import org.mkt.common.dto.VerifyTokenResponse;
import org.mkt.user.mapper.LoggedUserMapper;
import org.mkt.user.mapper.UserMapper;
import org.mkt.user.model.LoggedUser;
import org.mkt.user.model.User;
import org.mkt.user.repository.LoggedUserRepository;
import org.mkt.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final LoggedUserRepository loggedUserRepository;
    public final PasswordEncoder passwordEncoder;

    public Response<LoginResponse> doAuthenticate(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
        if (optionalUser.isPresent()) {
            if (passwordEncoder.matches(loginRequest.getPassword(), optionalUser.get().getPassword())) {
                LoggedUser loggedUser = new LoggedUser();
                loggedUser.setUser(optionalUser.get());
                loggedUser.setExpiredAt(LocalDateTime.now());
                loggedUser.setToken("%s-%s".formatted(UUID.randomUUID(), LocalDateTime.now()));
                loggedUser.setDeviceType(loginRequest.getDeviceType());
                loggedUserRepository.save(loggedUser);
                return new Response<>(1, LoggedUserMapper.INSTANCE.entityToRequest(loggedUser));
            }
        }
        return new Response<>(0, "Invalid username or password");
    }

    public Response<VerifyTokenResponse> doAuthorization(String token) {
        Optional<LoggedUser> loggedUserOptional = loggedUserRepository.findByToken(token);
        if (loggedUserOptional.isPresent()) {
            VerifyTokenResponse response = new VerifyTokenResponse(loggedUserOptional.get().getToken(),
                    loggedUserOptional.get().getExpiredAt(),
                    UserMapper.INSTANCE.entityToRequest(loggedUserOptional.get().getUser()));
            return new Response<>(1, response);
        }
        return new Response<>(0, "Invalid token");
    }

    public VerifyTokenResponse getByToken(String token) {
        Optional<LoggedUser> loggedUserOptional = loggedUserRepository.findByToken(token);
        if (loggedUserOptional.isPresent()) {
           return new VerifyTokenResponse(loggedUserOptional.get().getToken(),
                    loggedUserOptional.get().getExpiredAt(),
                    UserMapper.INSTANCE.entityToRequest(loggedUserOptional.get().getUser()));
        }
        return null;
    }
}
