package com.example.project.repository;

import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyTrafficEntity;
import com.example.project.entity.TrafficEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MyTrafficRepository extends JpaRepository<MyTrafficEntity,Long> {

    List<MyTrafficEntity> findByMemberEntity(MemberEntity memberEntity);
    Optional<MyTrafficEntity> findByMemberEntityAndMyTrafficLonAndMyTrafficLat(MemberEntity memberEntity,Double lon,Double lat);

    List<MyTrafficEntity> findByMemberEntityOrderByMyTrafficOrderNumber(MemberEntity memberEntity);


    @Query("select m from MyTrafficEntity m where m.memberEntity.memberId = :memberId and  m.myTrafficLat between :a and :b and m.myTrafficLon between :c and :d")
    List<MyTrafficEntity> findBetween(Long memberId , Double a , Double b , Double c , Double d);
}
