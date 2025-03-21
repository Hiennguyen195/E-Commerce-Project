package com.example.ecomerce.dto.request.cart;

public class CartRequest {

    private Long productId;
    private int quantity;

    public CartRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
