package org.mkt.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserResponse {
    private Long id;

    private String username;
    private String role;

    private String mobileNumber;

    private String password;

    private String email;

    private String firstName;
}
