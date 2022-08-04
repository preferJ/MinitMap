package com.example.project.service;

import com.example.project.dto.TrafficIntegratedDTO;
import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import com.example.project.repository.TrafficRepository;
import com.example.project.repository.TrafficTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrafficTestService {

    private final TrafficRepository trafficRepository;
    private final TrafficTimeRepository trafficTimeRepository;


    //ㅅㅎ 테스트 끝 주석
//    public List<TrafficIntegratedDTO> findAll() {
//        List<TrafficIntegratedDTO> trafficTestDTOList = new ArrayList<>();
//        List<TrafficEntity> trafficEntities = trafficRepository.findAll();
//        List<TrafficTimeEntity> trafficTimeEntities = trafficTimeRepository.findAll();
//        for (int i = 0; i < trafficEntities.size(); i++) {
//            trafficTestDTOList.add(TrafficIntegratedDTO.toTrafficTestDTO(trafficEntities.get(i), trafficTimeEntities.get(i)));
//        }
//        return trafficTestDTOList;
//    }
}
