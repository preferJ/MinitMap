package com.example.project.service;

import com.example.project.dto.BoardDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.TrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final TrafficRepository trafficRepository;

    //이현
    public void save(BoardDTO boardDTO, Long memberId) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(memberId);
        BoardEntity boardEntity = new BoardEntity();
        if (boardDTO.getBoardType().equals("신호")) {
            Optional<TrafficEntity> trafficEntity = trafficRepository.findById(boardDTO.getTrafficId());
            boardEntity = BoardEntity.toBoardTrafficSaveEntity(boardDTO, memberEntity.get(), trafficEntity.get());
        } else {
            boardEntity = BoardEntity.toBoardSaveEntity(boardDTO, memberEntity.get());
        }
        boardRepository.save(boardEntity);
    }
}
