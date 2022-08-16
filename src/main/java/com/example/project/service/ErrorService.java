package com.example.project.service;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.ErrorDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.ErrorEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.ErrorRepository;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.TrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ErrorService {
    private final ErrorRepository errorRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final TrafficRepository trafficRepository;

    public void save(ErrorDTO errorDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(errorDTO.getMemberId());
        System.out.println("optionalMemberEntity = " + optionalMemberEntity);
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            if (errorDTO.getBoardId() != null) {
                Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(errorDTO.getBoardId());
                System.out.println("optionalBoardEntity = " + optionalBoardEntity);
                if (optionalBoardEntity.isPresent()) {
                    BoardEntity boardEntity = optionalBoardEntity.get();
                    errorRepository.save(ErrorEntity.toBoardErrorSaveEntity(errorDTO, boardEntity, memberEntity));
                    BoardEntity boardEntity1 = boardRepository.findById(errorDTO.getBoardId()).get();
                    boardEntity.setBoardReport(boardEntity1.getBoardReport() + 1l);
                    boardRepository.save(boardEntity1);
                }
            } else {
                Optional<TrafficEntity> optionalTrafficEntity = trafficRepository.findById(errorDTO.getTrafficId());
                if (optionalTrafficEntity.isPresent()) {
                    TrafficEntity trafficEntity = optionalTrafficEntity.get();
                    errorRepository.save(ErrorEntity.toTrafficErrorSaveEntity(errorDTO, trafficEntity, memberEntity));
                }
            }

        }

    }

    public List<BoardDTO> findDistinctByBoardEntity() {
        // 신고를 받은 글 출력 메서드
        List<BoardEntity> boardEntities = errorRepository.findDistinct();
        List<BoardDTO> boardDTOS = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntities) {
            boardDTOS.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOS;
    }

    public List<ErrorDTO> findAllByBoardId(Long boardId) {
        // 해당 글의 신고 출력 메서드
        List<ErrorEntity> errorEntityList = errorRepository.findByBoardEntity(boardRepository.findByBoardId(boardId).get());
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        for (ErrorEntity error : errorEntityList) {
            errorDTOList.add(ErrorDTO.toErrorDTOEmail(error));
        }
        return errorDTOList;
    }

    public void updateManagerCheck(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<ErrorEntity> byBoardEntity = errorRepository.findByBoardEntity(boardEntity);
        for (ErrorEntity error : byBoardEntity) {
            error.setManagerCheck(true);
            errorRepository.save(error);
        }
    }
}
