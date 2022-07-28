package com.example.project.repository;

import com.example.project.entity.BoardEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.TrafficEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    Page<BoardEntity> findByBoardType(String q, Pageable pageable);

    Page<BoardEntity> findByBoardTypeLocation1AndBoardTypeLocation2(String local1 , String local2 , Pageable pageable);
    Page<BoardEntity> findByBoardTypeContainingAndBoardTypeLocation1ContainingAndBoardTypeLocation2Containing(String type , String local1 , String local2 , Pageable pageable);
    Page<BoardEntity> findByBoardTitleContaining(String search , Pageable pageable);

    @Modifying
    @Query(value = "update BoardEntity b set b.boardLike = b.boardLike + 1 where b.boardId = :id")
    int like(@Param("id") Long id);

    @Modifying
    @Query(value = "update BoardEntity b set b.boardDislike = b.boardDislike + 1 where b.boardId = :id")
    int dislike(@Param("id") Long id);

    @Modifying
    @Query(value = "update BoardEntity b set b.boardLike = b.boardLike - 1 where b.boardId = :id")
    int UnLike(@Param("id") Long id);

    @Modifying
    @Query(value = "update BoardEntity b set b.boardDislike = b.boardDislike - 1 where b.boardId = :id")
    int UnDislike(@Param("id") Long id);

    List<BoardEntity> findAllByBoardCreatedTimeBetweenOrderByBoardLikeDesc(LocalDateTime start, LocalDateTime end); // 전체
}
