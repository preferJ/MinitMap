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
    private LocalTime trafficApplyStart;
    private LocalTime trafficApplyEnd;
    private String startType;
    private LocalTime greenOn;
    private LocalTime redOn;
    private LocalTime setStartTime;
}
