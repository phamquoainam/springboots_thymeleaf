package com.hoainam.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hoainam.entity.Videos;

public interface VideosRepository extends JpaRepository<Videos, Long> {
    @Query("SELECT v FROM Videos v WHERE v.title LIKE %?1%")
    List<Videos> search(String keyword);
    Page<Videos> findByTitleContaining(String title, Pageable pageable);
}
