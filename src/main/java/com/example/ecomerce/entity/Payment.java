package com.example.ecomerce.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    private String paymentMethod; // CREDIT_CARD, PAYPAL, COD
    private String paymentStatus; // PAID, UNPAID
    private LocalDateTime paymentDate;

}
