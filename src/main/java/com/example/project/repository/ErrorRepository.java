package com.example.project.repository;

import com.example.project.entity.BoardEntity;
import com.example.project.entity.ErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ErrorRepository extends JpaRepository<ErrorEntity,Long> {


    @Query("select DISTINCT (e.boardEntity) from ErrorEntity e order by e.managerCheck asc")
    List<BoardEntity> findDistinct();

    List<ErrorEntity> findByBoardEntity(BoardEntity boardEntity);
    // 해당하는 글이 받은 오류신고들
}
