package com.example.project.service;

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
        if(optionalMemberEntity.isPresent()){
                MemberEntity memberEntity = optionalMemberEntity.get();
            if(errorDTO.getBoardId() != null){
                Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(errorDTO.getBoardId());
                if(optionalBoardEntity.isPresent()) {
                    BoardEntity boardEntity = optionalBoardEntity.get();
                    errorRepository.save(ErrorEntity.toBoardErrorSaveEntity(errorDTO,boardEntity, memberEntity));
                }
            }
            else{
                Optional<TrafficEntity> optionalTrafficEntity = trafficRepository.findById(errorDTO.getTrafficId());
                if(optionalTrafficEntity.isPresent()) {
                    TrafficEntity trafficEntity = optionalTrafficEntity.get();
                    errorRepository.save(ErrorEntity.toTrafficErrorSaveEntity(errorDTO, trafficEntity, memberEntity));
                }
            }
        }
    }
}
