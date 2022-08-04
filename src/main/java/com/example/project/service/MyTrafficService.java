package com.example.project.service;

import com.example.project.dto.MyTrafficDTO;
import com.example.project.dto.TrafficDTO;
import com.example.project.dto.TrafficIntegratedDTO;
import com.example.project.entity.*;
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
    private final TrafficTimeRepository trafficTimeRepository;
    private final TrafficRepository trafficRepository;


    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    //이현 중복신호등 체크
    public String saveCheck(Long id, Long loginId) {
        String check = "ok";
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        Optional<MemberEntity> memberEntity = memberRepository.findById(loginId);
        Optional<MyTrafficEntity> byMemberEntityAndTrafficEntity = myTrafficRepository.findByMemberEntityAndMyTrafficLonAndMyTrafficLat(memberEntity.get(), boardEntity.get().getTrafficEntity().getTrafficLon(), boardEntity.get().getTrafficEntity().getTrafficLat());
        if (byMemberEntityAndTrafficEntity.isPresent()) {
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
        for (TrafficTimeEntity trafficTimeEntity : byTrafficEntity) {
            trafficTimeRepository.save(TrafficTimeEntity.trafficToMyTraffic(trafficTimeEntity, save));
        }
        if (save.getMyTrafficId() != null) {
            return "ok";
        } else {
            return "no";
        }
    }


    public List<MyTrafficDTO> findByMemberId(Long id) {
        Optional<MemberEntity> byId = memberRepository.findById(id);
        List<MyTrafficEntity> byMemberEntity = myTrafficRepository.findByMemberEntity(byId.get());
        List<MyTrafficDTO> myTrafficDTOS = new ArrayList<>();
        for (MyTrafficEntity myTrafficEntity : byMemberEntity) {
            myTrafficDTOS.add(MyTrafficDTO.toSaveMyTrafficDTO(myTrafficEntity));
        }
        return myTrafficDTOS;
    }

    //선호 신호등 저장 메서드
    public Long save(MyTrafficDTO myTrafficDTO, Long loginId) {
        Optional<MemberEntity> byId = memberRepository.findById(loginId);
        myTrafficDTO.setMyTrafficOrderNumber(myTrafficRepository.count()+1);
        MyTrafficEntity save = myTrafficRepository.save(MyTrafficEntity.toSaveMyTrafficEntity(myTrafficDTO, byId.get()));
        return save.getMyTrafficId();
    }

    public List<TrafficIntegratedDTO> inBoundFindAll(String center, Long memberId) {
        System.out.println("MyTrafficService.inBoundFindAll");
        List<TrafficIntegratedDTO> trafficIntegratedDTOList = new ArrayList<>();
        String[] latlng = center.split(",");
        System.out.println(latlng);
        double lat = Double.parseDouble(latlng[0]);
        double lng = Double.parseDouble(latlng[1]);
        System.out.println(lat);
        System.out.println(lng);
        List<TrafficEntity> trafficEntityList = trafficRepository.findAll();
        List<MyTrafficEntity> myTrafficEntityList = myTrafficRepository.findAll();
        List<TrafficTimeEntity> trafficTimeEntityList = trafficTimeRepository.findAll();


        // 변수명 time 은 기준이 되는 trafficTime 객체
        for (TrafficTimeEntity time :trafficTimeEntityList) {
           if (time.getTrafficEntity().getTrafficId() == null){
               // 트래픽ID 가 null 이면 --> 마이트래픽
               trafficIntegratedDTOList.add(TrafficIntegratedDTO.toTrafficIntegratedDTO(time.getMyTrafficEntity(),time));

           }
        }

        return trafficIntegratedDTOList;
    }
}
