package com.example.project.dto;

import com.example.project.entity.TrafficTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Traffic2DTO {
    private Long trafficTimeId2;
    private Long trafficId2;
    private Long myTrafficId2;
    private Long trafficApplyStart2;
    private Long trafficApplyEnd2;
    private String startType2;
    private Long greenOn2;
    private Long redOn2;
    private Long setStartTime2;
}
