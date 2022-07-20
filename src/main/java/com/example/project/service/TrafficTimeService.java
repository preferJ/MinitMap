package com.example.project.service;

import com.example.project.dto.TrafficTimeDTO;
import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import com.example.project.repository.TrafficRepository;
import com.example.project.repository.TrafficTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
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
}
