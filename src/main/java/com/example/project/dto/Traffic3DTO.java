package com.example.project.dto;

import com.example.project.entity.TrafficTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Traffic3DTO {
    private Long trafficTimeId3;
    private Long trafficId3;
    private Long myTrafficId3;
    private Long trafficApplyStart3;
    private Long trafficApplyEnd3;
    private String startType3;
    private Long greenOn3;
    private Long redOn3;
    private Long setStartTime3;
}
