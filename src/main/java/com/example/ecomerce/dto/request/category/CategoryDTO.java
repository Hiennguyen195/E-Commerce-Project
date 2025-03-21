package com.example.ecomerce.dto.request.category;

import com.example.ecomerce.dto.request.product.ProductDTO;
import lombok.Setter;

import java.util.List;

public class CategoryDTO {

    private Long id;
    private String categoryName;
    @Setter
    private List<ProductDTO> products;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String categoryName, List<ProductDTO> products) {
        this.id = id;
        this.categoryName = categoryName;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

}
