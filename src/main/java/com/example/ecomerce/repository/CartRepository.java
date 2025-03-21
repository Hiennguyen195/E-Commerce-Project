package com.example.ecomerce.repository;

import com.example.ecomerce.entity.Cart;
import com.example.ecomerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findById(Long id);
    Optional<Cart> findByUserId(Long userId);
    Optional<Cart> findByUser(User user);
}
