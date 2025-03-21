package com.example.ecomerce.service;

import com.example.ecomerce.dto.request.CartProductRequest;
import com.example.ecomerce.dto.request.cart.CartItemDTO;
import com.example.ecomerce.entity.Cart;
import com.example.ecomerce.entity.CartItem;
import com.example.ecomerce.entity.Product;
import com.example.ecomerce.entity.User;
import com.example.ecomerce.repository.CartItemRepository;
import com.example.ecomerce.repository.CartRepository;
import com.example.ecomerce.repository.ProductRepository;
import com.example.ecomerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public String addToCart(CartProductRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUserId(request.getUserId()).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        if (request.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (request.getProductId() == null) {
            throw new IllegalArgumentException("Product ID must not be null");
        }
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < request.getQuantity()) {
            return "Not enough stock available!";
        }

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.getProductId()))
                .findFirst()
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    newItem.setQuantity(0);
                    return newItem;
                });

        cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        cartItemRepository.save(cartItem);

        int updatedRows = productRepository.decreaseStock(request.getProductId(), request.getQuantity());
        if (updatedRows == 0) {
            return "Stock update failed, product may be out of stock!";
        }

        return "Product added to cart successfully!";
    }

    @Transactional
    public String removeFromCart(CartProductRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (request.getProductId() == null) {
            throw new IllegalArgumentException("Product ID must not be null");
        }
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.getProductId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        cartItem.setQuantity(cartItem.getQuantity() - request.getQuantity());
        if (cartItem.getQuantity() <= 0) {
            cart.getItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        } else {
            cartItemRepository.save(cartItem);
        }

        int updatedRows = productRepository.increaseStock(request.getProductId(), request.getQuantity());
        if (updatedRows == 0) {
            return "Stock update failed, product may be out of stock!";
        }

        return "Product removed from cart successfully!";
    }


    public void clearCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = user.getCart();
        cart.clear();
    }

    public List<CartItemDTO> getCartItemsByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .map(cart -> cart.getItems().stream()
                        .map(item -> new CartItemDTO(
                                item.getProduct().getId(),
                                item.getProduct().getProductName(),
                                item.getQuantity(),
                                item.getProduct().getPrice()
                        ))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
