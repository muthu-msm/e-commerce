package org.mkt.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRequest {
    private Long orderID;

    private String orderNumber;

    private Long userId;

    private BigDecimal netAmount;

    private BigDecimal grossAmount;

    private BigDecimal deliveryCharge;
}
