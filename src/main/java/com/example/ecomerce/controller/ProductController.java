package com.example.ecomerce.controller;

import com.example.ecomerce.dto.request.product.ProductDTO;
import com.example.ecomerce.dto.request.product.ProductCreationRequest;
import com.example.ecomerce.dto.request.product.ProductPageRequest;
import com.example.ecomerce.entity.Product;
import com.example.ecomerce.repository.CategoryRepository;
import com.example.ecomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository CategoryRepository;

    @PostMapping
    Product createProduct(@RequestBody ProductCreationRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{productId}")
    Product updateProduct(@PathVariable Long productId, @RequestBody ProductCreationRequest request) {
        return productService.updateProduct(productId, request);
    }

    @GetMapping
    Page<ProductDTO> getAllProducts(ProductPageRequest request) {
        return productService.getProducts(request);
    }

    @GetMapping("/{productId}")
    Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/category/{categoryId}")
    List<ProductDTO> getProductsByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> product = productService.getProductsByCategory(categoryId);
        return product;
    }
}
