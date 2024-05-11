package org.mkt.manager.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponse {

    private Long orderID;

    private String orderNumber;

    private Long userId;

    private BigDecimal netAmount;

    private BigDecimal grossAmount;

    private BigDecimal deliveryCharge;
}
