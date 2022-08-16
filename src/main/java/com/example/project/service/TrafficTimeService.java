package com.example.project.service;

import com.example.project.dto.Traffic2DTO;
import com.example.project.dto.Traffic3DTO;
import com.example.project.dto.TrafficIntegratedDTO;
import com.example.project.dto.TrafficTimeDTO;
import com.example.project.entity.MyTrafficEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import com.example.project.repository.MyTrafficRepository;
import com.example.project.repository.TrafficRepository;
import com.example.project.repository.TrafficTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
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
        trafficTimeRepository.save(TrafficTimeEntity.toTrafficTimeSaveEntity(trafficTimeDTO, entity.get()));
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
        for (TrafficTimeEntity trafficTimeEntity : byTrafficEntity) {
            trafficTimeDTOList.add(TrafficTimeDTO.toTrafficTimeDTO(trafficTimeEntity));
        }

        return trafficTimeDTOList;
    }

    public List<TrafficTimeDTO> findByMyTrafficId(Long id) {
        MyTrafficEntity myTrafficEntity = myTrafficRepository.findById(id).get();
        List<TrafficTimeEntity> byTrafficEntity = trafficTimeRepository.findByMyTrafficEntity(myTrafficEntity);
        List<TrafficTimeDTO> trafficTimeDTOList = new ArrayList<>();
        for (TrafficTimeEntity trafficTimeEntity : byTrafficEntity) {
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

    public String timeCheck(Long id, Double start, Double end, Long timeId) {
        List<TrafficTimeEntity> byMyTrafficEntity = trafficTimeRepository.findByMyTrafficEntity(myTrafficRepository.findById(id).get());
        String check = "ok";
        for (TrafficTimeEntity trafficTimeEntity : byMyTrafficEntity) {
            if (trafficTimeEntity.getTrafficTimeId() != timeId) {
                if (trafficTimeEntity.getTrafficApplyStart() < start && trafficTimeEntity.getTrafficApplyEnd() > start) {
                    check = "no";
                } else if (trafficTimeEntity.getTrafficApplyStart() < end && trafficTimeEntity.getTrafficApplyEnd() > start) {
                    check = "no";
                }
            }
        }
        return check;
    }

    public void adminSave(Long id, TrafficTimeDTO trafficTimeDTO, Traffic2DTO traffic2DTO, Traffic3DTO traffic3DTO) {
        TrafficEntity trafficEntity = trafficRepository.findById(id).get();
        trafficTimeRepository.save(TrafficTimeEntity.toTrafficTimeSaveEntity(trafficTimeDTO,trafficEntity));
        trafficTimeRepository.save(TrafficTimeEntity.adminSave2(traffic2DTO,trafficEntity));
        trafficTimeRepository.save(TrafficTimeEntity.adminSave3(traffic3DTO,trafficEntity));
    }

    public void adminUpdate(Long id, TrafficTimeDTO trafficTimeDTO, Traffic2DTO traffic2DTO, Traffic3DTO traffic3DTO) {
        TrafficEntity trafficEntity = trafficRepository.findById(id).get();
        trafficTimeRepository.save(TrafficTimeEntity.toTrafficTimeUpdateEntity(trafficTimeDTO,trafficEntity));
        trafficTimeRepository.save(TrafficTimeEntity.UpdateAdminSave2(traffic2DTO,trafficEntity));
        trafficTimeRepository.save(TrafficTimeEntity.UpdateAdminSave3(traffic3DTO,trafficEntity));
    }

    @Transactional
    public List<TrafficIntegratedDTO> findTime(List<TrafficEntity> trafficEntities, List<MyTrafficEntity> myTrafficEntities) {
        LocalTime now = LocalTime.now();
        Long nowTime = now.getHour()*10000l + now.getMinute() * 100l + now.getSecond();  // 시간을 Long 타입으로 변환

        List<TrafficIntegratedDTO> trafficIntegratedDTOList = new ArrayList<>();
        // 트래픽리스트
        for (TrafficEntity trafficEntity : trafficEntities){
            List<TrafficTimeEntity> byTrafficEntity = trafficTimeRepository.findByTrafficEntity(trafficEntity);
            // 결국 얻게되는 시간
        // 신호가 1개면 그냥 걔만가져감
        if (byTrafficEntity.size() <=1){
            trafficIntegratedDTOList.add(TrafficIntegratedDTO.toTrafficIntegratedDTO(trafficEntity,byTrafficEntity.get(0)));
        //  신호가 2개 이상이면 구분해서 가져감
        }else{
            int timeCheck = -1;
            // 사이값이 아니면 리턴 없어서 오류뜨니 트라이캐치
            try{
                timeCheck = trafficTimeRepository.findByBetween(trafficEntity,nowTime);
            }catch (Exception e){

            }
            if (timeCheck == -1){
                timeCheck = trafficTimeRepository.findByTimeCheck(trafficEntity, nowTime);
            }
            TrafficTimeEntity trafficTimeEntity = trafficTimeRepository.findById((long) timeCheck).get();
            trafficIntegratedDTOList.add(TrafficIntegratedDTO.toTrafficIntegratedDTO(trafficEntity,trafficTimeEntity));
        }}


        for (MyTrafficEntity trafficEntity : myTrafficEntities){
            List<TrafficTimeEntity> byTrafficEntity = trafficTimeRepository.findByMyTrafficEntity(trafficEntity);
            // 결국 얻게되는 시간
            // 신호가 1개면 그냥 걔만가져감
            if (byTrafficEntity.size() <=1){
                trafficIntegratedDTOList.add(TrafficIntegratedDTO.toTrafficIntegratedDTO(trafficEntity,byTrafficEntity.get(0)));
                //  신호가 2개 이상이면 구분해서 가져감
            }else{
                int timeCheck = -1;
                // 사이값이 아니면 리턴 없어서 오류뜨니 트라이캐치
                try{
                    timeCheck = trafficTimeRepository.findByBetweenMY(trafficEntity,nowTime);
                }catch (Exception e){

                }
                if (timeCheck == -1){
                    timeCheck = trafficTimeRepository.findByTimeCheckMy(trafficEntity, nowTime);
                }
                TrafficTimeEntity trafficTimeEntity = trafficTimeRepository.findById((long) timeCheck).get();
                trafficIntegratedDTOList.add(TrafficIntegratedDTO.toTrafficIntegratedDTO(trafficEntity,trafficTimeEntity));
            }}

        return trafficIntegratedDTOList;
    }
}