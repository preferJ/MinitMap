package com.example.project.service;

import com.example.project.dto.TrafficDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.TrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrafficService {

    private final TrafficRepository trafficRepository;
    private final MemberRepository memberRepository;

    public Long save(TrafficDTO trafficDTO, Long memberId) {
        Optional<MemberEntity> entity = memberRepository.findById(memberId);
        Long returnId = trafficRepository.save(TrafficEntity.trafficSaveEntity(trafficDTO, entity.get())).getTrafficId();
        return returnId;
    }


    public List<TrafficDTO> findAll() {
        List<TrafficEntity> trafficEntities = trafficRepository.findAll();
        List<TrafficDTO> trafficDTOList = new ArrayList<>();
        for (TrafficEntity trafficEntity : trafficEntities) {
            trafficDTOList.add(TrafficDTO.toTrafficDTO(trafficEntity));
        }
        return trafficDTOList;
    }


    public List<TrafficDTO> findByMemberId(Long id) {
        List<TrafficEntity> byMemberEntity = trafficRepository.findByMemberEntity(memberRepository.findById(id).get());
        List<TrafficDTO> trafficDTOList = new ArrayList<>();
        for (TrafficEntity trafficEntity : byMemberEntity){
            trafficDTOList.add(TrafficDTO.toTrafficDTO(trafficEntity));
        }
        return trafficDTOList;
    }

    public Long adminSave(TrafficDTO trafficDTO,Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).get();
        return trafficRepository.save(TrafficEntity.trafficSaveEntity(trafficDTO,memberEntity)).getTrafficId();
    }

    public List<TrafficDTO> findByEmail(String email) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(email).get();
        List<TrafficEntity> byMemberEntity = trafficRepository.findByMemberEntity(memberEntity);
        List<TrafficDTO> trafficDTOList = new ArrayList<>();
        for (TrafficEntity trafficEntity : byMemberEntity){
            trafficDTOList.add(TrafficDTO.toTrafficDTO(trafficEntity));
        }
        return trafficDTOList;
    }
}
