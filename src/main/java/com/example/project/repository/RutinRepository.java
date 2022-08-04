package com.example.project.repository;

import com.example.project.entity.MemberEntity;
import com.example.project.entity.RutinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RutinRepository extends JpaRepository<RutinEntity,Long> {
    List<RutinEntity> findByMemberEntity(MemberEntity memberEntity);

    List<RutinEntity> findByMemberEntityOrderByRutinNumberAsc(MemberEntity memberEntity);
    List<RutinEntity> findByMemberEntityOrderByRutinNumberDesc(MemberEntity memberEntity);
}
