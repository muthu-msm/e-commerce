package org.mkt.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private String imageUrls;

    private BigDecimal price;

    private int maxQuantity;

    private int minQuantity;

    private int availableQuantity;

    private String category;

    private String brand;

    private String additionalDetails;
}
