package org.mkt.common.dto;

import lombok.Data;
import org.mkt.common.dto.enums.DeviceType;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private DeviceType deviceType;
}
