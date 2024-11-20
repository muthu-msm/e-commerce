package org.mkt.common.dto;

import lombok.Data;

@Data
public class AddressRequest {
    private Long id;

    private UserRequest user;

    private String mobileNumber;

    private String addressLine1;

    private String addressLine2;

    private String landMark;

    private String district;

    private String state;

    private String country;

    private String zipCode;
}
