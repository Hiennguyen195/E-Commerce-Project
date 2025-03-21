package com.example.ecomerce.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductCreationRequest {

    private String productName;
    private String description;
    private BigDecimal price;
    private int stock;
    private Long categoryId;
}