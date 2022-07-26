package com.example.project.service;

import com.example.project.dto.LikeCheckDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.LikeCheckEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.LikeCheckRepository;
import com.example.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeCheckService {
    private final LikeCheckRepository likeCheckRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    public LikeCheckDTO findCheck(Long id, Long memberId) {
        BoardEntity boardEntity = boardRepository.findById(id).get();
        MemberEntity memberEntity = memberRepository.findById(memberId).get();
        LikeCheckEntity byBoardEntityAndMemberEntity = likeCheckRepository.findByBoardEntityAndMemberEntity(boardEntity, memberEntity);
        if (byBoardEntityAndMemberEntity == null){
            return null;
        }else{
            return LikeCheckDTO.toLikeBoardCheckDTO(likeCheckRepository.findByBoardEntityAndMemberEntity(boardEntity, memberEntity));
        }
    }
}
