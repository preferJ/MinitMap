package com.example.project.repository;

import com.example.project.entity.BoardEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.TrafficEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    List<BoardEntity> findByBoardType(String type);
}
