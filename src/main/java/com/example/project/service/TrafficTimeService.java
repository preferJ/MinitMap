package com.example.project.service;

import com.example.project.dto.TrafficDTO;
import com.example.project.dto.TrafficTimeDTO;
import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import com.example.project.repository.TrafficRepository;
import com.example.project.repository.TrafficTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrafficTimeService {
    private final TrafficRepository trafficRepository;
    private final TrafficTimeRepository trafficTimeRepository;
    public void save(Long id, TrafficTimeDTO trafficTimeDTO) {
        Optional<TrafficEntity> entity = trafficRepository.findById(id);
        trafficTimeRepository.save(TrafficTimeEntity.toTrafficTimeSaveEntity(trafficTimeDTO,entity.get()));

    }
    public List<TrafficTimeDTO> findAll() {
        List<TrafficTimeEntity> trafficTimeEntities = trafficTimeRepository.findAll();
        List<TrafficTimeDTO> trafficTimeDTOList = new ArrayList<>();
        for (TrafficTimeEntity trafficTimeEntity : trafficTimeEntities) {
            trafficTimeDTOList.add(TrafficTimeDTO.toTrafficTimeDTO(trafficTimeEntity));
        }
        return trafficTimeDTOList;
    }

    public TrafficTimeDTO findByTrafficId(Long trafficId) {
        Optional<TrafficEntity> entity = trafficRepository.findById(trafficId);
        return TrafficTimeDTO.toTrafficTimeDTO(trafficTimeRepository.findByTrafficEntity(entity.get()));
    }
}
