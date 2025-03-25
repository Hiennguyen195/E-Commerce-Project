package com.example.ecomerce.entity;

import com.example.ecomerce.entity.ENUM.PaymentMethod;
import com.example.ecomerce.entity.ENUM.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;// CREDIT_CARD, PAYPAL, COD

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus; // PENDING, PAID, FAILED

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentDate;

}
