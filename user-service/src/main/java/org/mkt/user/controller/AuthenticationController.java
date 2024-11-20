package org.mkt.user.controller;


import lombok.RequiredArgsConstructor;
import org.mkt.common.dto.LoginRequest;
import org.mkt.common.dto.LoginResponse;
import org.mkt.common.dto.Response;
import org.mkt.common.dto.UserRequest;
import org.mkt.common.dto.UserResponse;
import org.mkt.common.dto.VerifyTokenResponse;
import org.mkt.user.service.AuthenticationService;
import org.mkt.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.doAuthenticate(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<Response<VerifyTokenResponse>> validateToken(@RequestParam String token) {
        return ResponseEntity.ok(authenticationService.doAuthorization(token));
    }

}
