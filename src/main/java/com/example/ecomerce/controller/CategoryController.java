package com.example.ecomerce.controller;

import com.example.ecomerce.dto.request.category.CategoryCreationRequest;
import com.example.ecomerce.dto.request.category.CategoryDTO;
import com.example.ecomerce.entity.Category;
import com.example.ecomerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    Category createCategory(@RequestBody CategoryCreationRequest request) {
        return categoryService.createCategory(request);
    }

    @GetMapping
    List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    CategoryDTO getCategoryWithProducts(@PathVariable Long categoryId) {
        return categoryService.getCategoryWithProducts(categoryId);
    }
}
