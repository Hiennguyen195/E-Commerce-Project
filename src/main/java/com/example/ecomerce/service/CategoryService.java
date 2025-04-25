package com.example.ecomerce.service;

import com.example.ecomerce.dto.request.category.CategoryCreationRequest;
import com.example.ecomerce.dto.request.category.CategoryDTO;
import com.example.ecomerce.dto.request.product.ProductDTO;
import com.example.ecomerce.entity.Category;
import com.example.ecomerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(CategoryCreationRequest request) {
        Category category = new Category();

        category.setCategoryName(request.getCategoryName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDTO getCategoryWithProducts(Long categoryId) {
        Category category = categoryRepository.findCategoryWithProducts(categoryId);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        List<ProductDTO> productDTOs = category.getProducts().stream()
                .map(p -> new ProductDTO(p.getId(), p.getProductName(), p.getDescription(), p.getPrice(), p.getStock(), p.getCategory().getCategoryName()))
                .collect(Collectors.toList());

        return new CategoryDTO(category.getId(), category.getCategoryName(), productDTOs);
    }


}
