package org.mkt.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyTokenResponse {
    private String token;
    private LocalDateTime expiredAt;
    private UserResponse user;
}
