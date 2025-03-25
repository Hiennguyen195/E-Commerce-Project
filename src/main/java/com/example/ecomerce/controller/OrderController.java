package com.example.ecomerce.controller;

import com.example.ecomerce.dto.request.order.OrderCreationRequest;
import com.example.ecomerce.dto.request.order.OrderDTO;
import com.example.ecomerce.entity.Order;
import com.example.ecomerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<OrderDTO> checkout(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.checkout(userId));
    }

    @GetMapping("/{orderId}")
    public List<OrderDTO> getOrder(@PathVariable Long orderId) {
        return orderService.getAllOrders(orderId);
    }
}
