package com.example.project.dto;

import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficTestDTO {
    private Long trafficId;
    private Long memberId;
    private String trafficName;
    private Double trafficLat;
    private Double trafficLon;
    private Long trafficLike;
    private Long trafficDisLike;

    private Long trafficTimeId;
    private Long trafficApplyStart;
    private Long trafficApplyEnd;
    private String startType;
    private Long greenOn;
    private Long redOn;
    private Long setStartTime;

    //ㅅㅎ 신호등 합친거 메서드
    public static TrafficTestDTO toTrafficTestDTO(TrafficEntity trafficEntity, TrafficTimeEntity trafficTimeEntity) {
        TrafficTestDTO trafficTestDTO = new TrafficTestDTO();

        trafficTestDTO.setTrafficId(trafficEntity.getTrafficId());
        trafficTestDTO.setMemberId(trafficEntity.getTrafficId());
        trafficTestDTO.setTrafficName(trafficEntity.getTrafficName());
        trafficTestDTO.setTrafficLat(trafficEntity.getTrafficLat());
        trafficTestDTO.setTrafficLon(trafficEntity.getTrafficLon());
        trafficTestDTO.setTrafficLike(trafficEntity.getTrafficLike());
        trafficTestDTO.setTrafficDisLike(trafficEntity.getTrafficDislike());
        trafficTestDTO.setTrafficId(trafficTimeEntity.getTrafficTimeId());
        trafficTestDTO.setTrafficApplyStart(trafficTimeEntity.getTrafficApplyStart());
        trafficTestDTO.setTrafficApplyEnd(trafficTimeEntity.getTrafficApplyEnd());
        trafficTestDTO.setStartType(trafficTimeEntity.getStartType());
        trafficTestDTO.setGreenOn(trafficTimeEntity.getGreenOn());
        trafficTestDTO.setRedOn(trafficTimeEntity.getRedOn());
        trafficTestDTO.setSetStartTime(trafficTimeEntity.getSetStartTime());
        return trafficTestDTO;
    }


}
