package com.example.project.entity;

import com.example.project.dto.RutinDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "rutin")
public class RutinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rutinId")
    private Long rutinId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;
    // 시작지 이름
    @Column(name = "startPoint", nullable = false)
    private String stratPoint;

    @Column(name = "startPointLat", nullable = false)
    private Double startPointLat;

    @Column(name = "startPointLon", nullable = false)
    private Double startPointLon;
    // 도착지 이름
    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "endPointLat", nullable = false)
    private Double endPointLat;

    @Column(name = "endPointLon", nullable = false)
    private Double endPointLon;

    public static RutinEntity toRutinSaveEntity(RutinDTO rutinDTO, MemberEntity memberEntity){
        RutinEntity rutinEntity = new RutinEntity();
        rutinEntity.setStratPoint(rutinDTO.getStartPoint());
        rutinEntity.setStartPointLat(rutinDTO.getStartPointLat());
        rutinEntity.setStartPointLon(rutinDTO.getStartPointLon());
        rutinEntity.setDestination(rutinDTO.getDestination());
        rutinEntity.setEndPointLat(rutinDTO.getEndPointLat());
        rutinEntity.setEndPointLon(rutinDTO.getEndPointLon());
        rutinEntity.setMemberEntity(memberEntity);
        return rutinEntity;
    }

    public static RutinEntity toRutinUpdateEntity(RutinDTO rutinDTO, MemberEntity memberEntity){
        RutinEntity rutinEntity = new RutinEntity();
        rutinEntity.setRutinId(rutinDTO.getRutinId());
        rutinEntity.setStratPoint(rutinDTO.getStartPoint());
        rutinEntity.setStartPointLat(rutinDTO.getStartPointLat());
        rutinEntity.setStartPointLon(rutinDTO.getStartPointLon());
        rutinEntity.setDestination(rutinDTO.getDestination());
        rutinEntity.setEndPointLat(rutinDTO.getEndPointLat());
        rutinEntity.setEndPointLon(rutinDTO.getEndPointLon());
        rutinEntity.setMemberEntity(memberEntity);
        return rutinEntity;
    }
}
