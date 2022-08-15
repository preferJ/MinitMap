package com.example.project.repository;

import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyTrafficEntity;
import com.example.project.entity.TrafficEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrafficRepository extends JpaRepository<TrafficEntity,Long> {
    List<TrafficEntity> findByMemberEntity(MemberEntity memberEntity);

    List<TrafficEntity> findByMemberEntityOrderByTrafficLikeAsc(MemberEntity memberEntity);
    List<TrafficEntity> findByMemberEntityOrderByTrafficLikeDesc(MemberEntity memberEntity);

    List<TrafficEntity> findByMemberEntityOrderByTrafficDislikeAsc(MemberEntity memberEntity);
    List<TrafficEntity> findByMemberEntityOrderByTrafficDislikeDesc(MemberEntity memberEntity);

    @Query("select t from TrafficEntity t where t.trafficLat between :a and :b and t.trafficLon between :c and :d")
    List<TrafficEntity> findBetween(Double a , Double b , Double c , Double d);
}
