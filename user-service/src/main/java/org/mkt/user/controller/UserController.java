package org.mkt.user.controller;


import lombok.RequiredArgsConstructor;
import org.mkt.common.dto.Response;
import org.mkt.common.dto.UserRequest;
import org.mkt.common.dto.UserResponse;
import org.mkt.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Response<UserResponse>> create(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @PutMapping
    public ResponseEntity<Response<UserResponse>> update(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.update(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<UserResponse>> create(@PathVariable Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

}
