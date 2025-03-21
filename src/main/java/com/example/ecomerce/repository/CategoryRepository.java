package com.example.ecomerce.repository;


import com.example.ecomerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryById(Long id);

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.products WHERE c.id = :categoryId")
    Category findCategoryWithProducts(@Param("categoryId") Long categoryId);
}
