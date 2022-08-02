package com.example.project.repository;

import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyPlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyPlaceRepository extends JpaRepository<MyPlaceEntity,Long> {
    List<MyPlaceEntity> findByMemberEntity(MemberEntity memberEntity);
    Optional<MyPlaceEntity> findByMemberEntityAndMyPlaceLatAndMyPlaceLon(MemberEntity memberEntity, Double lat, Double lon);
}
