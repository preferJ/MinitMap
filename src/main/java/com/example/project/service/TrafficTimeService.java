package com.example.project.service;

import com.example.project.dto.TrafficDTO;
import com.example.project.dto.TrafficTimeDTO;
import com.example.project.entity.MyTrafficEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import com.example.project.repository.MyTrafficRepository;
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
    private final MyTrafficRepository myTrafficRepository;
    private final TrafficTimeRepository trafficTimeRepository;
    public void save(Long id, TrafficTimeDTO trafficTimeDTO) {
        Optional<MyTrafficEntity> entity = myTrafficRepository.findById(id);
        System.out.println(trafficTimeDTO);
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

    public List<TrafficTimeDTO> findByTrafficId(Long trafficId) {
        Optional<TrafficEntity> entity = trafficRepository.findById(trafficId);
        List<TrafficTimeEntity> byTrafficEntity = trafficTimeRepository.findByTrafficEntity(entity.get());
        List<TrafficTimeDTO> trafficTimeDTOList = new ArrayList<>();
        for (TrafficTimeEntity trafficTimeEntity : byTrafficEntity){
            trafficTimeDTOList.add(TrafficTimeDTO.toTrafficTimeDTO(trafficTimeEntity));
        }

        return trafficTimeDTOList;
    }

    public List<TrafficTimeDTO> findByMyTrafficId(Long id) {
        MyTrafficEntity myTrafficEntity = myTrafficRepository.findById(id).get();
        List<TrafficTimeEntity> byTrafficEntity = trafficTimeRepository.findByMyTrafficEntity(myTrafficEntity);
        List<TrafficTimeDTO> trafficTimeDTOList = new ArrayList<>();
        for (TrafficTimeEntity trafficTimeEntity : byTrafficEntity){
            trafficTimeDTOList.add(TrafficTimeDTO.toMyTrafficTimeDTO(trafficTimeEntity));
        }

        return trafficTimeDTOList;
    }

    public void deleteById(Long id) {
        trafficTimeRepository.deleteById(id);
    }

    public void update(TrafficTimeDTO trafficTimeDTO) {
        TrafficTimeEntity trafficTimeEntity = trafficTimeRepository.findById(trafficTimeDTO.getTrafficTimeId()).get();
        trafficTimeEntity.setTrafficApplyStart(trafficTimeDTO.getTrafficApplyStart());
        trafficTimeEntity.setTrafficApplyEnd(trafficTimeDTO.getTrafficApplyEnd());
        trafficTimeEntity.setGreenOn(trafficTimeDTO.getGreenOn());
        trafficTimeEntity.setRedOn(trafficTimeDTO.getRedOn());
        trafficTimeEntity.setSetStartTime(trafficTimeDTO.getSetStartTime());
        trafficTimeRepository.save(trafficTimeEntity);
    }
}
