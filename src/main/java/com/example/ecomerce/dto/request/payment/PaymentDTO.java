package com.example.ecomerce.dto.request.payment;

import com.example.ecomerce.entity.enums.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long orderId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

}
