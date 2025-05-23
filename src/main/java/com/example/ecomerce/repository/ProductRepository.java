package com.example.ecomerce.repository;

import com.example.ecomerce.dto.request.product.ProductDTO;
import com.example.ecomerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new com.example.ecomerce.dto.request.product.ProductDTO(p.id, p.productName, p.description, p.price, p.stock, p.category.categoryName ) " +
            "FROM Product p " +
            "WHERE p.category.id = :categoryId")
    Page<ProductDTO> findProductsByCategory(@Param("categoryId") Long categoryId, Pageable pageable);

    @Modifying
    @Query("UPDATE Product p " +
            "SET p.stock = p.stock - :quantity " +
            "WHERE p.id = :productId AND p.stock >= :quantity")
    int decreaseStock(@Param("productId") Long productId, @Param("quantity") int quantity);

    @Modifying
    @Query("UPDATE Product p " +
            "SET p.stock = p.stock + :quantity " +
            "WHERE p.id = :productId")
    int increaseStock(Long productId, int quantity);

    @Override
    Page<Product> findAll(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product findByProductId(Long id);
}