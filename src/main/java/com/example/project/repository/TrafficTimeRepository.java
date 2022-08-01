package com.example.project.repository;

import com.example.project.entity.MyTrafficEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrafficTimeRepository extends JpaRepository<TrafficTimeEntity,Long> {
    List<TrafficTimeEntity> findByTrafficEntity (TrafficEntity trafficEntity);

    List<TrafficTimeEntity> findByMyTrafficEntity (MyTrafficEntity MyTrafficEntity);

    void deleteByTrafficEntity(TrafficEntity trafficEntity);
}
