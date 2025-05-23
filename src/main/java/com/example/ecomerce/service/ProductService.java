package com.example.ecomerce.service;

import com.example.ecomerce.dto.request.product.ProductCreationRequest;
import com.example.ecomerce.dto.request.product.ProductDTO;
import com.example.ecomerce.dto.request.product.ProductPageRequest;
import com.example.ecomerce.dto.request.product.ProductUpdateRequest;
import com.example.ecomerce.entity.Category;
import com.example.ecomerce.entity.Notification;
import com.example.ecomerce.entity.Product;
import com.example.ecomerce.mapper.ProductMapper;
import com.example.ecomerce.repository.CategoryRepository;
import com.example.ecomerce.repository.NotificationRepository;
import com.example.ecomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    public Page<ProductDTO> getProductsByCategory(Long id, ProductPageRequest request) {
        return productRepository.findProductsByCategory(id, request.toPageable());

    }

    public Product createProduct(ProductCreationRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        product.setStock(request.getStock());

        Notification noti = new Notification();
        noti.setMessage(" | Sản phẩm: " + product.getProductName() +" đã được thêm mới.");
        notificationRepository.save(noti);

        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, ProductUpdateRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        Notification noti = new Notification();
        noti.setMessage(" | Sản phẩm có ID " + productId + ": "+ product.getProductName() +" đã được cập nhật.");
        notificationRepository.save(noti);

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findByProductId(id);
        productRepository.deleteById(id);
        Notification noti = new Notification();
        noti.setMessage(" | Sản phẩm có ID " + id + ": "+ product.getProductName() +" đã bị xóa.");
        notificationRepository.save(noti);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Page<ProductDTO> getProducts(ProductPageRequest request) {
        return productRepository.findAll(request.toPageable())
                .map(ProductMapper::toDTO); //Convert to DTO
    }

    // Count the total number of products in the database
    public Long countProducts() {
        return productRepository.count();
    }
}
