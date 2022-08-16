package com.example.project.repository;

import com.example.project.dto.ErrorDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.ErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ErrorRepository extends JpaRepository<ErrorEntity,Long> {


    @Query("select DISTINCT (e.boardEntity) from ErrorEntity e order by e.boardEntity.boardHits asc")
    List<BoardEntity> findDistinct();

    List<ErrorEntity> findByBoardEntity(BoardEntity boardEntity);

}
