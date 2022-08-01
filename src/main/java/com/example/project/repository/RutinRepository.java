package com.example.project.repository;

import com.example.project.entity.MemberEntity;
import com.example.project.entity.RutinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutinRepository extends JpaRepository<RutinEntity,Long> {
    List<RutinEntity> findByMemberEntity(MemberEntity memberEntity);
}
