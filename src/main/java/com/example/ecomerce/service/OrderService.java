package com.example.ecomerce.service;

import com.example.ecomerce.dto.request.order.OrderCreationRequest;
import com.example.ecomerce.entity.Cart;
import com.example.ecomerce.entity.CartItem;
import com.example.ecomerce.entity.Order;
import com.example.ecomerce.entity.OrderItem;
import com.example.ecomerce.repository.OrderItemRepository;
import com.example.ecomerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    public Order checkout(Long userId) {
        // Lấy giỏ hàng của user
        Cart cart = cartService.getCartByUserId(userId);
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Giỏ hàng đang trống!");
        }

        // Tạo đơn hàng
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));

            totalAmount = totalAmount.add(orderItem.getPrice());
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalAmount);

        // Lưu đơn hàng
        orderRepository.save(order);

        // Xóa giỏ hàng sau khi checkout
        cartService.clearCart(userId);

        return order;
    }

}
