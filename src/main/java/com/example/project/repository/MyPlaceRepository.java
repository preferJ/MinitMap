package com.example.project.repository;

import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyPlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPlaceRepository extends JpaRepository<MyPlaceEntity,Long> {
    List<MyPlaceEntity> findByMemberEntity(MemberEntity memberEntity);
}
