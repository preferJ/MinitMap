package com.example.project.service;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.ErrorDTO;
import com.example.project.dto.MemberDTO;
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
//    오류신고 목록 메서드
//        List<ErrorEntity> errorEntityList = errorRepository.findAll();
//        List<ErrorDTO> errorDTOList = new ArrayList<>();
//        for(ErrorEntity error: errorEntityList) {
//            errorDTOList.add(ErrorDTO.toErrorDTO(error));
//            System.out.println("error = " + error);
//        }
//        return errorDTOList;
//    }
}
