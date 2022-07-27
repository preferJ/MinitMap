package com.example.project.repository;

import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrafficTimeRepository extends JpaRepository<TrafficTimeEntity,Long> {
    TrafficTimeEntity findByTrafficEntity (TrafficEntity trafficEntity);
}
