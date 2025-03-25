package com.example.ecomerce.service;


import com.example.ecomerce.dto.request.order.OrderDTO;
import com.example.ecomerce.entity.Cart;
import com.example.ecomerce.entity.CartItem;
import com.example.ecomerce.entity.ENUM.OrderStatus;
import com.example.ecomerce.entity.Order;
import com.example.ecomerce.entity.OrderItem;
import com.example.ecomerce.repository.CartItemRepository;
import com.example.ecomerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public OrderDTO checkout(Long userId) {
        // Lấy giỏ hàng của user
        Cart cart = cartService.getCartByUserId(userId);
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Giỏ hàng đang trống!");
        }

        // Tạo đơn hàng
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

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

        // Xóa toàn bộ CartItem trước
        cartItemRepository.deleteCartItemById(cart.getId());

        // Xóa giỏ hàng sau khi checkout
        cartService.clearCart(userId);

        // Tạo và trả về OrderDTO
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserName(userService.getUserById(userId).get().getUserName());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalPrice(order.getTotalPrice());

        return orderDTO;
    }

    public List<OrderDTO> getAllOrders(Long userId) {
        List<Order> orders = orderRepository.findOrderById(userId);
        List<OrderDTO> orderDTOs = new ArrayList<>();

        for (Order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUserName(order.getUser().getUserName());
            orderDTO.setOrderDate(order.getOrderDate());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setTotalPrice(order.getTotalPrice());
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }

}
