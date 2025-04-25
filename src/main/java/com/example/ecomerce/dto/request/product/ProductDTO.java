package com.example.ecomerce.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String productName;
    private String categoryName;
    private String description;
    private BigDecimal price;
    private int stock;

    public ProductDTO(Long id, String productName, String description, BigDecimal price, Integer stock, String categoryName) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryName = categoryName;
    }

}

