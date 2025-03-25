package com.example.ecomerce.service;

import com.example.ecomerce.dto.request.payment.PaymentDTO;
import com.example.ecomerce.entity.ENUM.PaymentStatus;
import com.example.ecomerce.entity.Order;
import com.example.ecomerce.entity.ENUM.OrderStatus;
import com.example.ecomerce.entity.Payment;
import com.example.ecomerce.repository.OrderRepository;
import com.example.ecomerce.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    public String payForOrder(PaymentDTO paymentDTO) {
        Order order = orderRepository.findById(paymentDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Check if the order is pending => can be paid
        if (order.getStatus() == OrderStatus.PENDING) {

            Payment payment = new Payment();
            payment.setOrder(order);
            payment.setPaymentStatus(PaymentStatus.PAID);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            // Save the payment
            paymentRepository.save(payment);

            // Update the order status
            order.setStatus(OrderStatus.PAID);

            return "Payment successful";
        }else { // If the order is not pending => cannot be paid
            return "Order is not available for payment";
        }
    }
}
