package org.mkt.common.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserRequest {

    private Long id;

    private String username;

    private String role;

    private String mobileNumber;

    private String password;

    private String email;

    private String firstName;
}
