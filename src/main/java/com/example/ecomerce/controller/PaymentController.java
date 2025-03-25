package com.example.ecomerce.controller;

import com.example.ecomerce.dto.request.payment.PaymentDTO;
import com.example.ecomerce.service.OrderService;
import com.example.ecomerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/pay")
    public String payForOrder(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.payForOrder(paymentDTO);
    }
}

