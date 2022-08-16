package com.example.project.dto;

import com.example.project.entity.TrafficBookmarkEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficBookmarkDTO {
    private Long trafficBookmarkId;
    private Long memberId;
    private Long trafficId;
    private Long myTrafficId;
    private String trafficName;
    private double lat;
    private double lon;

    public static TrafficBookmarkDTO toTrafficBookmarkDTO(TrafficBookmarkEntity trafficBookmarkEntity){
        TrafficBookmarkDTO trafficBookmarkDTO = new TrafficBookmarkDTO();
        trafficBookmarkDTO.setTrafficBookmarkId(trafficBookmarkEntity.getTrafficBookmarkId());
        trafficBookmarkDTO.setMemberId(trafficBookmarkEntity.getMemberEntity().getMemberId());
        trafficBookmarkDTO.setTrafficId(trafficBookmarkEntity.getTrafficEntity().getTrafficId());
        trafficBookmarkDTO.setMyTrafficId(trafficBookmarkEntity.getMyTrafficEntity().getMyTrafficId());
        return trafficBookmarkDTO;
    }
}
