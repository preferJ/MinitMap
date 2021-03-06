package com.example.project.repository;

import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyTrafficEntity;
import com.example.project.entity.TrafficEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyTrafficRepository extends JpaRepository<MyTrafficEntity,Long> {
    Optional<MyTrafficEntity> findByMemberEntityAndTrafficEntity (MemberEntity memberEntity , TrafficEntity trafficEntity);
}
