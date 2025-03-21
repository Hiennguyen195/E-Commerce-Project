package com.example.ecomerce.dto.request;

import com.example.ecomerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProductRequest {

    private Long userId;
    private Long productId;
    private int quantity;


}
