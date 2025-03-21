package com.example.ecomerce.dto.request.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CartItemDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal price;

    public CartItemDTO(Long productId, String productName, int quantity, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}
