package com.example.ecomerce.controller;

import com.example.ecomerce.dto.request.CartProductRequest;

import com.example.ecomerce.dto.request.cart.CartItemDTO;
import com.example.ecomerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestBody CartProductRequest request) {
        String message = cartService.addToCart(request);
        return message;
    }

    @DeleteMapping("/remove")
    public String removeFromCart(@RequestBody CartProductRequest request) {
        String message = cartService.removeFromCart(request);
        return message;
    }

    @PutMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemDTO>> getCartItems(@PathVariable Long userId) {
        List<CartItemDTO> cartItems = cartService.getCartItemsByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }

}
