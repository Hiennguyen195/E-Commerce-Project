package com.example.ecomerce.mapper;

import com.example.ecomerce.dto.request.product.ProductDTO;
import com.example.ecomerce.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }

    public static List<ProductDTO> toDTOList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
