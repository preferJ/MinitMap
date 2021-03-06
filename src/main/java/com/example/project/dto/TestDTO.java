package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {
    private Long trafficTimeId;
    private Long trafficId;
    private Long trafficApplyStart;
    private Long trafficApplyEnd;
    private String startType;
    private Long greenOn;
    private Long redOn;
    private Long setStartTime;
}
