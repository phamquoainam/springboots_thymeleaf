package com.hoainam.service.impl;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoainam.entity.Videos;
import com.hoainam.repository.VideosRepository;
import com.hoainam.service.VideosService;

@Service
public class VideosServiceImpl implements VideosService {

    @Autowired
    VideosRepository videoRepository;

    @Override
    public List<Videos> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public Videos findById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }

    @Override
    public Videos create(Videos video) {
        return videoRepository.save(video);
    }

    @Override
    public Videos update(Videos video) {
        return videoRepository.save(video);
    }

    @Override
    public void delete(Long id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
        }
    }

    @Override
    public List<Videos> search(String keyword) {
        return (keyword == null || keyword.isEmpty())
                ? videoRepository.findAll()
                : videoRepository.search(keyword);
    }
    
    @Override
    public Page<Videos> findAll(Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    @Override
    public Page<Videos> search(String title, Pageable pageable) {
        return videoRepository.findByTitleContaining(title, pageable);
    }

}
