package com.example.project.dto;

import com.example.project.entity.TrafficTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficTimeDTO {
    private Long trafficTimeId;
    private Long trafficId;
    private Long myTrafficId;
    private Long trafficApplyStart;
    private Long trafficApplyEnd;
    private String startType;
    private Long greenOn;
    private Long redOn;
    private Long setStartTime;

    public static TrafficTimeDTO toTrafficTimeDTO(TrafficTimeEntity trafficTimeEntity){
        TrafficTimeDTO trafficTimeDTO = new TrafficTimeDTO();
        trafficTimeDTO.setTrafficTimeId(trafficTimeEntity.getTrafficTimeId());
        trafficTimeDTO.setTrafficId(trafficTimeEntity.getTrafficEntity().getTrafficId());
        trafficTimeDTO.setTrafficApplyStart(trafficTimeEntity.getTrafficApplyStart());
        trafficTimeDTO.setTrafficApplyEnd(trafficTimeEntity.getTrafficApplyEnd());
        trafficTimeDTO.setStartType(trafficTimeEntity.getStartType());
        trafficTimeDTO.setGreenOn(trafficTimeEntity.getGreenOn());
        trafficTimeDTO.setRedOn(trafficTimeEntity.getRedOn());
        trafficTimeDTO.setSetStartTime(trafficTimeEntity.getSetStartTime());
        return trafficTimeDTO;
    }

    public static TrafficTimeDTO toMyTrafficTimeDTO(TrafficTimeEntity trafficTimeEntity){
        TrafficTimeDTO trafficTimeDTO = new TrafficTimeDTO();
        trafficTimeDTO.setTrafficTimeId(trafficTimeEntity.getTrafficTimeId());
        trafficTimeDTO.setMyTrafficId(trafficTimeEntity.getMyTrafficEntity().getMyTrafficId());
        trafficTimeDTO.setTrafficApplyStart(trafficTimeEntity.getTrafficApplyStart());
        trafficTimeDTO.setTrafficApplyEnd(trafficTimeEntity.getTrafficApplyEnd());
        trafficTimeDTO.setStartType(trafficTimeEntity.getStartType());
        trafficTimeDTO.setGreenOn(trafficTimeEntity.getGreenOn());
        trafficTimeDTO.setRedOn(trafficTimeEntity.getRedOn());
        trafficTimeDTO.setSetStartTime(trafficTimeEntity.getSetStartTime());
        return trafficTimeDTO;
    }
}
