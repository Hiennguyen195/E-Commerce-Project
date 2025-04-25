package com.example.ecomerce.controller;

import com.example.ecomerce.dto.request.product.ProductDTO;
import com.example.ecomerce.dto.request.product.ProductCreationRequest;
import com.example.ecomerce.dto.request.product.ProductPageRequest;
import com.example.ecomerce.dto.request.product.ProductUpdateRequest;
import com.example.ecomerce.dto.response.APIResponse;
import com.example.ecomerce.entity.Product;
import com.example.ecomerce.repository.CategoryRepository;
import com.example.ecomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository CategoryRepository;

    @PostMapping
    APIResponse<Product> createProduct(@RequestBody ProductCreationRequest request) {
        APIResponse<Product> apiResponse = new APIResponse<>();
        apiResponse.setResult(productService.createProduct(request));
        return apiResponse;
    }

    @PutMapping("/{productId}")
    Product updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest request) {
        return productService.updateProduct(productId, request);
    }

    @GetMapping
    Page<ProductDTO> getAllProducts(@ModelAttribute ProductPageRequest request) {
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

    @GetMapping("/category")
    Page<ProductDTO> getProductsByCategory(@RequestParam (name = "categoryId", required = false) Long id,
                                           @ModelAttribute ProductPageRequest request) {
        return productService.getProductsByCategory(id, request);

    }

    @GetMapping("/count")
    public ResponseEntity<Long> countProduct() {
        long count = productService.countProducts();
        return ResponseEntity.ok(count);
    }
}
