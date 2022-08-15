package com.example.project.repository;

import com.example.project.entity.MyTrafficEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TrafficTimeRepository extends JpaRepository<TrafficTimeEntity, Long> {
    List<TrafficTimeEntity> findByTrafficEntity(TrafficEntity trafficEntity);

    List<TrafficTimeEntity> findByMyTrafficEntity(MyTrafficEntity MyTrafficEntity);

    void deleteByTrafficEntity(TrafficEntity trafficEntity);

    @Query(value = "select * from traffic_time where traffic_id = :id and traffic_apply_end > :time1 order by ABS(traffic_apply_start - :time1) limit 1", nativeQuery = true)
    @Transactional
    int findByTimeCheck(@Param(value = "id") TrafficEntity trafficEntity, @Param(value = "time1") Long time1);


    @Query(value = "select * from traffic_time where traffic_id = :id and :time1 between traffic_apply_start and traffic_apply_end", nativeQuery = true)
    @Transactional
    int findByBetween(@Param(value = "id") TrafficEntity trafficEntity, @Param(value = "time1") Long time1);
}
