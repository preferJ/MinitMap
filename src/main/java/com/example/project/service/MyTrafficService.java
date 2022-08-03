package com.example.project.service;

import com.example.project.dto.MyTrafficDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyTrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import com.example.project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyTrafficService {
    private final MyTrafficRepository myTrafficRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final TrafficTimeRepository trafficTimeRepository;

    //이현 중복신호등 체크
    public String saveCheck(Long id, Long loginId) {
        String check = "ok";
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        Optional<MemberEntity> memberEntity = memberRepository.findById(loginId);
        Optional<MyTrafficEntity> byMemberEntityAndTrafficEntity = myTrafficRepository.findByMemberEntityAndMyTrafficLonAndMyTrafficLat(memberEntity.get(), boardEntity.get().getTrafficEntity().getTrafficLon(),boardEntity.get().getTrafficEntity().getTrafficLat());
        if (byMemberEntityAndTrafficEntity.isPresent()){
            check = "no";
        }

        return check;
    }

//     마이 트래픽에 신호등 등록하기
    public String save(Long id, Long loginId, String name) {
        Optional<BoardEntity> board = boardRepository.findById(id);
        Optional<MemberEntity> byId = memberRepository.findById(loginId);
        // 신호등 저장
        MyTrafficEntity save = myTrafficRepository.save(MyTrafficEntity.toSaveMyTrafficEntity(board.get().getTrafficEntity().getTrafficLat(), board.get().getTrafficEntity().getTrafficLon(), name, byId.get()));
        // 신호등 시간 저장
        List<TrafficTimeEntity> byTrafficEntity = trafficTimeRepository.findByTrafficEntity(board.get().getTrafficEntity());
        for (TrafficTimeEntity trafficTimeEntity : byTrafficEntity){
            trafficTimeRepository.save(TrafficTimeEntity.trafficToMyTraffic(trafficTimeEntity,save));
        }
        if (save.getMyTrafficId() != null){
            return "ok";
        }else{
            return "no";
        }
    }


    public List<MyTrafficDTO> findByMemberId(Long id) {
        Optional<MemberEntity> byId = memberRepository.findById(id);
        List<MyTrafficEntity> byMemberEntity = myTrafficRepository.findByMemberEntity(byId.get());
        List<MyTrafficDTO> myTrafficDTOS = new ArrayList<>();
        for (MyTrafficEntity myTrafficEntity : byMemberEntity){
            myTrafficDTOS.add(MyTrafficDTO.toSaveMyTrafficDTO(myTrafficEntity));
        }
        return myTrafficDTOS;
    }
}
