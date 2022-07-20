package com.example.project.service;

import com.example.project.dto.TrafficDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.TrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrafficService {

    private final TrafficRepository trafficRepository;
    private final MemberRepository memberRepository;
    public Long save(TrafficDTO trafficDTO ,HttpSession session) {
        Optional<MemberEntity> entity = memberRepository.findById((Long) session.getAttribute("id"));
       Long returnId =  trafficRepository.save(TrafficEntity.trafficSaveEntity(trafficDTO,entity.get())).getTrafficId();
        return returnId;
}
}
