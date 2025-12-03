package com.hoainam.service.impl;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoainam.entity.Category;
import com.hoainam.repository.CategoryRepository;
import com.hoainam.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
    }
    
    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> search(String name, Pageable pageable) {
        return categoryRepository.findByCategoryNameContaining(name, pageable);
    }

}
