package com.example.ecomerce.repository;

import com.example.ecomerce.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String userName);

    @Override
    Page<User> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM user WHERE role = :role",
            countQuery = "SELECT count(*) FROM user WHERE role = :role",
            nativeQuery = true)
    Page<User> findUsersByRole(@Param("role") String role, Pageable pageable);

    @Query(value = "SELECT * FROM user " +
            "WHERE ((:userName IS NULL OR user_name LIKE %:userName%) " +
            "OR (:email IS NULL OR email LIKE %:email%)) " +
            "AND (:role IS NULL OR role = :role)",
            countQuery = "SELECT COUNT(*) FROM user " +
                    "WHERE ((:userName IS NULL OR user_name LIKE %:userName%) " +
                    "OR (:email IS NULL OR email LIKE %:email%)) " +
            "AND (:role IS NULL OR role = :role)",
            nativeQuery = true)
    Page<User> findByUserNameOrEmailContainingIgnoreCase(@Param("userName") String userName,
                                                         @Param("email") String email,
                                                         @Param("role") String role,
                                                         Pageable pageable);

}
