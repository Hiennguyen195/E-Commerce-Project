package com.example.ecomerce.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private String userName;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
    private String status;
}
