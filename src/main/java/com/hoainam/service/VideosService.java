package com.hoainam.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hoainam.entity.Videos;

public interface VideosService {
    List<Videos> findAll();

    Videos findById(Long id);

    Videos create(Videos video);

    Videos update(Videos video);

    void delete(Long id);

    List<Videos> search(String keyword);
    
    Page<Videos> findAll(Pageable pageable);
    
    Page<Videos> search(String title, Pageable pageable);
}
