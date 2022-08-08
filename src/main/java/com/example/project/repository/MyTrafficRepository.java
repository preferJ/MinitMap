package com.example.project.repository;

import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyTrafficEntity;
import com.example.project.entity.TrafficEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyTrafficRepository extends JpaRepository<MyTrafficEntity,Long> {

    List<MyTrafficEntity> findByMemberEntity(MemberEntity memberEntity);
    Optional<MyTrafficEntity> findByMemberEntityAndMyTrafficLonAndMyTrafficLat(MemberEntity memberEntity,Double lon,Double lat);

    List<MyTrafficEntity> findByMemberEntityOrderByMyTrafficOrderNumber(MemberEntity memberEntity);
}
