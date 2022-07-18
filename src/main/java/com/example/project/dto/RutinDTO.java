package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RutinDTO {
    private Long rutinId;
    private Long memberId;
    private String startPoint;
    private Double startPointLat;
    private Double startPointLon;
    private String destination;
    private Double endPointLat;
    private Double endPointLon;
}
