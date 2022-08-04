package com.example.project.service;

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


//    public List<ErrorDTO> findAll() {
//        List<ErrorEntity> errorEntityList = errorRepository.findAll();
//        List<ErrorDTO> errorDTOList = new ArrayList<>();
//        for(ErrorEntity error: errorEntityList){
//            errorDTOList.add(ErrorDTO.toErrorDTO(error));
//        }
//        return errorDTOList;
//    }

    public List<ErrorDTO> findDistinctByBoardEntity() {
        // 신고를 받은 글 출력 메서드  / 지금은 중복 글은 하나만 출력 될 수 있게 수정 중
        List<ErrorEntity> errorEntityList = errorRepository.findDistinct();
        System.out.println("errorEntityList = " + errorEntityList);
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        for(ErrorEntity error: errorEntityList){
            errorDTOList.add(ErrorDTO.toErrorDTO(error));
        }
        return errorDTOList;
    }

    public List<ErrorDTO> findAllByBoardId(Long boardId) {
        // 해당 글의 신고 출력 메서드
       List<ErrorEntity> errorEntityList = errorRepository.findByBoardEntity(boardRepository.findByBoardId(boardId).get());
       List<ErrorDTO> errorDTOList = new ArrayList<>();
       for(ErrorEntity error: errorEntityList){
           errorDTOList.add(ErrorDTO.toErrorDTO(error));
       }
       return errorDTOList;
    }
}