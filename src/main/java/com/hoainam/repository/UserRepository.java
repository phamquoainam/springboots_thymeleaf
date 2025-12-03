package com.hoainam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; 

import com.hoainam.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

    User findByEmail(String email);
    
    Page<User> findByUsernameContaining(String username, Pageable pageable);
}
