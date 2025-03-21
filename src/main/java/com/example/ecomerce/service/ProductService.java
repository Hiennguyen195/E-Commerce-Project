package com.example.ecomerce.service;

import com.example.ecomerce.dto.request.product.ProductCreationRequest;
import com.example.ecomerce.dto.request.product.ProductDTO;
import com.example.ecomerce.entity.Category;
import com.example.ecomerce.entity.Product;
import com.example.ecomerce.repository.CategoryRepository;
import com.example.ecomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository CategoryRepository;

//    public List<Product> getProductsByCategory(Category category) {
//        return productRepository.findByCategory(category);
//    }

    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return productRepository.findProductsByCategory(categoryId);
    }

    public Product createProduct(ProductCreationRequest request) {
        Category category = CategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        product.setStock(request.getStock());

        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, ProductCreationRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = CategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        product.setStock(request.getStock());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
