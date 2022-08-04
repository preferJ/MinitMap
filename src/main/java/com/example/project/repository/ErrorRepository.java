package com.example.project.repository;

import com.example.project.entity.BoardEntity;
import com.example.project.entity.ErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ErrorRepository extends JpaRepository<ErrorEntity,Long> {

//    @Modifying
//    @Query(value = "SELECT DISTINCT board_id FROM error" , nativeQuery = true)
//    List<ErrorEntity> findDistinctByBoardEntity();

    @Query("select DISTINCT (e.boardEntity) from ErrorEntity e")
    List<ErrorEntity> findDistinct();
    List<ErrorEntity> findByBoardEntity(BoardEntity boardEntity);
    // 해당하는 글이 받은 오류신고들
}
