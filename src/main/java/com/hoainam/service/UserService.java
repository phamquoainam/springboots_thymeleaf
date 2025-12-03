package com.hoainam.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hoainam.entity.User;

public interface UserService {
    User findById(String username);

    User findByEmail(String email);

    List<User> findAll();

    User create(User user);

    User update(User user);

    void delete(String username);

    User login(String username, String password);

    // Register
    boolean register(User user);

    boolean checkExistEmail(String email);

    boolean checkExistUsername(String username);

    boolean checkExitsPhone(String phone);

    boolean updatePassword(String email, String password);
    
    Page<User> findAll(Pageable pageable);
    
    Page<User> search(String username, Pageable pageable);
}
