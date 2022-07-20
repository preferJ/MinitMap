package com.example.project.repository;

import com.example.project.entity.TrafficBookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrafficBookmarkRepository extends JpaRepository<TrafficBookmarkEntity,Long> {
}
