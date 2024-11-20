package org.mkt.common.dto;

import lombok.Data;
import org.mkt.common.dto.enums.DeviceType;

import java.time.LocalDateTime;

@Data
public class LoginResponse {
    private String token;
    private LocalDateTime expiredAt;
}
