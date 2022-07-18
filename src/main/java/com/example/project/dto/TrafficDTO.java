package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficDTO {
    private Long trafficId;
    private Long memberId;
    private String trafficName;
    private Double trafficLat;
    private Double trafficLon;
    private boolean bookmark;
    private Long trafficLike;
    private Long trafficDisLike;
}
