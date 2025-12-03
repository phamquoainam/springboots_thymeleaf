package com.hoainam.service.impl;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoainam.entity.User;
import com.hoainam.repository.UserRepository;
import com.hoainam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteById(username);
            ;
        }
    }

    @Override
    public User login(String username, String password) {

        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())
                || userRepository.existsByPhone(user.getPhone())) {
            return false;
        }
        user.setActive(true);
        user.setAdmin(false);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean checkExitsPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public boolean updatePassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(password);
            userRepository.save(user);
            return true;

        }
        return false;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> search(String username, Pageable pageable) {
        return userRepository.findByUsernameContaining(username, pageable);
    }

}
