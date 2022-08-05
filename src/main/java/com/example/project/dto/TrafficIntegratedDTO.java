package com.example.project.dto;

import com.example.project.entity.MyTrafficEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.entity.TrafficTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficIntegratedDTO {
    private Long trafficId;
    private Long memberId;
    private Long myTrafficId;
    private String myTrafficName;
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
    private Long leftTime;


    //ㅅㅎ 신호등 합친거 메서드
    //ㅅㅎ 통합해야해서 일단 주석 다시 만들어야함
//    public static TrafficTestDTO toTrafficTestDTO(TrafficEntity trafficEntity, TrafficTimeEntity trafficTimeEntity) {
//        TrafficTestDTO trafficTestDTO = new TrafficTestDTO();
//        LocalTime now = LocalTime.now();         // 현재시간 출력
//        System.out.println(now);
//        int hour = now.getHour();
//        int minute = now.getMinute();
//        int second = now.getSecond();
//        Long nowTime = Long.valueOf(hour * 10000 + minute * 100 + second);
//        trafficTestDTO.setTrafficId(trafficEntity.getTrafficId());
//        trafficTestDTO.setMemberId(trafficEntity.getTrafficId());
//        trafficTestDTO.setTrafficLat(trafficEntity.getTrafficLat());
//        trafficTestDTO.setTrafficLon(trafficEntity.getTrafficLon());
//        trafficTestDTO.setTrafficLike(trafficEntity.getTrafficLike());
//        trafficTestDTO.setTrafficDisLike(trafficEntity.getTrafficDislike());
//        trafficTestDTO.setTrafficId(trafficTimeEntity.getTrafficTimeId());
//        trafficTestDTO.setTrafficApplyStart(trafficTimeEntity.getTrafficApplyStart());
//        trafficTestDTO.setTrafficApplyEnd(trafficTimeEntity.getTrafficApplyEnd());
//        trafficTestDTO.setGreenOn(trafficTimeEntity.getGreenOn());
//        trafficTestDTO.setRedOn(trafficTimeEntity.getRedOn());
//        Long g = trafficTimeEntity.getGreenOn();
//        Long r = trafficTimeEntity.getRedOn();
//        Long st = trafficTimeEntity.getSetStartTime();
//        Long timeGap = nowTime-st;
//        if (timeGap > 0){
//            if (timeGap%(g+r) < g){
//                trafficTestDTO.setStartType(1L);
//                trafficTestDTO.setLeftTime(g-timeGap%(g+r));
//            }else{
//                trafficTestDTO.setStartType(-1L);
//                trafficTestDTO.setLeftTime((g+r)-timeGap%(g+r));
//            }
//        }else{
//            timeGap = Math.abs(timeGap);
//            if (timeGap%(g+r) < g){
//                trafficTestDTO.setStartType(-1L);
//                trafficTestDTO.setLeftTime(timeGap%(g+r));
//            }else{
//                trafficTestDTO.setStartType(1L);
//                trafficTestDTO.setLeftTime(timeGap%(g+r)-g);
//            }
//
//        }
//        return trafficTestDTO;
//    }

    public static TrafficIntegratedDTO toTrafficIntegratedDTO(MyTrafficEntity myTrafficEntity, TrafficTimeEntity trafficTimeEntity) {
        LocalTime now = LocalTime.now();         // 현재시간 출력
        System.out.println(now);
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        Long nowTime = Long.valueOf(hour * 10000 + minute * 100 + second);
        // 현재 시간 정보


        TrafficIntegratedDTO integratedDTO = new TrafficIntegratedDTO();

        integratedDTO.setMyTrafficId(myTrafficEntity.getMyTrafficId());
        integratedDTO.setMyTrafficName(myTrafficEntity.getMyTrafficName());
        // id , name 값
        integratedDTO.setTrafficLat(myTrafficEntity.getMyTrafficLat());
        integratedDTO.setTrafficLon(myTrafficEntity.getMyTrafficLon());
        // 위도 경도

        integratedDTO.setGreenOn(trafficTimeEntity.getGreenOn());
        integratedDTO.setRedOn(trafficTimeEntity.getRedOn());
        integratedDTO.setTrafficApplyStart(trafficTimeEntity.getTrafficApplyStart());
        integratedDTO.setTrafficApplyEnd(trafficTimeEntity.getTrafficApplyEnd());
        integratedDTO.setSetStartTime(trafficTimeEntity.getSetStartTime());
        integratedDTO.setLeftTime(1L);
        integratedDTO.setStartType(trafficTimeEntity.getStartType());
        // 신호 관련

        Long g = trafficTimeEntity.getGreenOn();
        Long r = trafficTimeEntity.getRedOn();
        Long st = trafficTimeEntity.getSetStartTime();
        Long timeGap = nowTime - st;
        if (timeGap > 0) {
            if (timeGap % (g + r) < g) {
                integratedDTO.setStartType("GREEN");
                integratedDTO.setLeftTime(g - timeGap % (g + r));
            } else {
                integratedDTO.setStartType("RED");
                integratedDTO.setLeftTime((g + r) - timeGap % (g + r));
            }
        } else {
            timeGap = Math.abs(timeGap);
            if (timeGap % (g + r) < g) {
                integratedDTO.setStartType("RED");
                integratedDTO.setLeftTime(timeGap % (g + r));
            } else {
                integratedDTO.setStartType("GREEN");
                integratedDTO.setLeftTime(timeGap % (g + r) - g);
            }
        }
        // 시작 신호 및 남은시간 계산하고 셋 해줌줌


        return integratedDTO;
    }


    public static TrafficIntegratedDTO toTrafficIntegratedDTO(TrafficEntity trafficEntity, TrafficTimeEntity trafficTimeEntity) {
        LocalTime now = LocalTime.now();         // 현재시간 출력
        System.out.println(now);
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        Long nowTime = Long.valueOf(hour * 10000 + minute * 100 + second);
        // 현재 시간 정보


        TrafficIntegratedDTO integratedDTO = new TrafficIntegratedDTO();

        integratedDTO.setTrafficId(integratedDTO.getTrafficId());
        // id , name 값
        integratedDTO.setTrafficLat(trafficEntity.getTrafficLat());
        integratedDTO.setTrafficLon(trafficEntity.getTrafficLat());
        // 위도 경도

        integratedDTO.setGreenOn(trafficTimeEntity.getGreenOn());
        integratedDTO.setRedOn(trafficTimeEntity.getRedOn());
        integratedDTO.setTrafficApplyStart(trafficTimeEntity.getTrafficApplyStart());
        integratedDTO.setTrafficApplyEnd(trafficTimeEntity.getTrafficApplyEnd());
        integratedDTO.setSetStartTime(trafficTimeEntity.getSetStartTime());
        integratedDTO.setLeftTime(1L);
        integratedDTO.setStartType(trafficTimeEntity.getStartType());
        // 신호 관련

        Long g = trafficTimeEntity.getGreenOn();
        Long r = trafficTimeEntity.getRedOn();
        Long st = trafficTimeEntity.getSetStartTime();
        Long timeGap = nowTime - st;
        if (timeGap > 0) {
            if (timeGap % (g + r) < g) {
                integratedDTO.setStartType("GREEN");
                integratedDTO.setLeftTime(g - timeGap % (g + r));
            } else {
                integratedDTO.setStartType("RED");
                integratedDTO.setLeftTime((g + r) - timeGap % (g + r));
            }
        } else {
            timeGap = Math.abs(timeGap);
            if (timeGap % (g + r) < g) {
                integratedDTO.setStartType("RED");
                integratedDTO.setLeftTime(timeGap % (g + r));
            } else {
                integratedDTO.setStartType("GREEN");
                integratedDTO.setLeftTime(timeGap % (g + r) - g);
            }
        }
        // 시작 신호 및 남은시간 계산하고 셋 해줌줌


        return integratedDTO;
    }


}