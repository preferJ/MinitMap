package com.example.project.repository;

import com.example.project.dto.LikeCheckDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.LikeCheckEntity;
import com.example.project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCheckRepository extends JpaRepository<LikeCheckEntity,Long> {
    LikeCheckEntity findByBoardEntity (BoardEntity boardEntity);

    LikeCheckEntity findByBoardEntityAndMemberEntity (BoardEntity boardEntity , MemberEntity memberEntity);
}
