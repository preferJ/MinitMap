package com.example.project.dto;

import com.example.project.entity.RutinEntity;
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
    private String rutinName;

    public static RutinDTO toRutinDTO(RutinEntity rutinEntity){
        RutinDTO rutinDTO = new RutinDTO();
        rutinDTO.setRutinId(rutinEntity.getRutinId());
        rutinDTO.setMemberId(rutinEntity.getMemberEntity().getMemberId());
        rutinDTO.setStartPoint(rutinEntity.getStratPoint());
        rutinDTO.setStartPointLat(rutinEntity.getStartPointLat());
        rutinDTO.setStartPointLon(rutinEntity.getStartPointLon());
        rutinDTO.setDestination(rutinEntity.getDestination());
        rutinDTO.setEndPointLat(rutinEntity.getEndPointLat());
        rutinDTO.setEndPointLon(rutinEntity.getEndPointLon());
        rutinDTO.setRutinName(rutinEntity.getRutinName());
        return rutinDTO;
    }
}
