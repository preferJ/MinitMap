package com.example.project.dto;

import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

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
    private Long startType;
    private Long greenOn;
    private Long redOn;
    private Long setStartTime;
    private Long leftTime;

    //ㅅㅎ 신호등 합친거 메서드
    public static TrafficTestDTO toTrafficTestDTO(TrafficEntity trafficEntity, TrafficTimeEntity trafficTimeEntity) {
        TrafficTestDTO trafficTestDTO = new TrafficTestDTO();
        LocalTime now = LocalTime.now();         // 현재시간 출력
        System.out.println(now);
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        Long nowTime = Long.valueOf(hour * 10000 + minute * 100 + second);
        trafficTestDTO.setTrafficId(trafficEntity.getTrafficId());
        trafficTestDTO.setMemberId(trafficEntity.getTrafficId());
        trafficTestDTO.setTrafficLat(trafficEntity.getTrafficLat());
        trafficTestDTO.setTrafficLon(trafficEntity.getTrafficLon());
        trafficTestDTO.setTrafficLike(trafficEntity.getTrafficLike());
        trafficTestDTO.setTrafficDisLike(trafficEntity.getTrafficDislike());
        trafficTestDTO.setTrafficId(trafficTimeEntity.getTrafficTimeId());
        trafficTestDTO.setTrafficApplyStart(trafficTimeEntity.getTrafficApplyStart());
        trafficTestDTO.setTrafficApplyEnd(trafficTimeEntity.getTrafficApplyEnd());
        trafficTestDTO.setGreenOn(trafficTimeEntity.getGreenOn());
        trafficTestDTO.setRedOn(trafficTimeEntity.getRedOn());
        Long g = trafficTimeEntity.getGreenOn();
        Long r = trafficTimeEntity.getRedOn();
        Long st = trafficTimeEntity.getSetStartTime();
        Long timeGap = nowTime-st;
        if (timeGap > 0){
            if (timeGap%(g+r) < g){
                trafficTestDTO.setStartType(1L);
                trafficTestDTO.setLeftTime(g-timeGap%(g+r));
            }else{
                trafficTestDTO.setStartType(-1L);
                trafficTestDTO.setLeftTime((g+r)-timeGap%(g+r));
            }
        }else{
            timeGap = Math.abs(timeGap);
            if (timeGap%(g+r) < g){
                trafficTestDTO.setStartType(-1L);
                trafficTestDTO.setLeftTime(timeGap%(g+r));
            }else{
                trafficTestDTO.setStartType(1L);
                trafficTestDTO.setLeftTime(timeGap%(g+r)-g);
            }

        }


        return trafficTestDTO;
    }


}
