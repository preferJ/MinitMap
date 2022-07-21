package com.example.project.service;

import com.example.project.dto.TrafficDTO;
import com.example.project.dto.TrafficTestDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.TrafficRepository;
import com.example.project.repository.TrafficTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrafficTestService {

    private final TrafficRepository trafficRepository;
    private final TrafficTimeRepository trafficTimeRepository;


    public List<TrafficTestDTO> findAll() {
        List<TrafficTestDTO> trafficTestDTOList = new ArrayList<>();
        List<TrafficEntity> trafficEntities = trafficRepository.findAll();
        List<TrafficTimeEntity> trafficTimeEntities = trafficTimeRepository.findAll();
        for (int i = 0; i < trafficEntities.size(); i++) {
            trafficTestDTOList.add(TrafficTestDTO.toTrafficTestDTO(trafficEntities.get(i), trafficTimeEntities.get(i)));
        }
        return trafficTestDTOList;
    }
}
