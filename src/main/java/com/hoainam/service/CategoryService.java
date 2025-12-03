package com.hoainam.service;

import java.util.List;

import org.springframework.data.domain.Page;      
import org.springframework.data.domain.Pageable;  

import com.hoainam.entity.Category;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    Category create(Category category);

    Category update(Category category);

    void delete(Long id);
   
    Page<Category> findAll(Pageable pageable);


    Page<Category> search(String name, Pageable pageable);
}