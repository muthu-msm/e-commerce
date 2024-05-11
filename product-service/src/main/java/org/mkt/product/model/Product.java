package org.mkt.product.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "products")
@Data
public class Product extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(columnDefinition = "jsonb")
    private String imageUrls;

    private BigDecimal price;

    private int maxQuantity;

    private int minQuantity;

    private int availableQuantity;

    private String category;

    private String brand;

    @Column(columnDefinition = "jsonb")
    private String additionalDetails;
}
