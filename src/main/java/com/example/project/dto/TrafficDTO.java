package com.example.project.dto;

import com.example.project.entity.TrafficEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficDTO {
    private Long trafficId;
    private Long memberId;
    private Double trafficLat;
    private Double trafficLon;
    private Long trafficLike;
    private Long trafficDislike;
    private Long boardId;


    public static TrafficDTO toTrafficDTO(TrafficEntity trafficEntity){
        TrafficDTO trafficDTO = new TrafficDTO();
        trafficDTO.setTrafficId(trafficEntity.getTrafficId());
        trafficDTO.setMemberId(trafficEntity.getMemberEntity().getMemberId());
        trafficDTO.setTrafficLat(trafficEntity.getTrafficLat());
        trafficDTO.setTrafficLon(trafficEntity.getTrafficLon());
        trafficDTO.setTrafficLike(trafficEntity.getTrafficLike());
        trafficDTO.setTrafficDislike(trafficEntity.getTrafficDislike());
        trafficDTO.setBoardId(trafficEntity.getBoardId());
        return trafficDTO;
    }


}
