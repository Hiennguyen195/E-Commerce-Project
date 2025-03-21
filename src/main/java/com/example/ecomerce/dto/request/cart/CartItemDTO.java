package com.example.ecomerce.dto.request.cart;

import com.example.ecomerce.entity.CartItem;
import lombok.Getter;

public class CartDTO {

    @Getter
    private Long id;
    @Getter
    private CartItem cartItem;

    public CartDTO() {
    }

    public CartDTO(Long id, CartItem cartItem) {
        this.id = id;
        this.cartItem = cartItem;
    }

}
