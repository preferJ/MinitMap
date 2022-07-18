package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPlaceDTO {
    private Long myPlaceId;
    private Long memberId;
    private String myPlaceName;
    private Double myPlaceLat;
    private Double myPlaceLon;
    private Long icon;
    private boolean bookmark;
}
