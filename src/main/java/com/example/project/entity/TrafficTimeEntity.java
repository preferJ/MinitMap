package com.example.project.entity;

import com.example.project.dto.TrafficTimeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "trafficTime")
public class TrafficTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trafficTimeId")
    private Long trafficTimeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trafficId", nullable = false)
    private TrafficEntity trafficEntity;

    @Column(name = "trafficApplyStart", nullable = false)
    private Long trafficApplyStart;

    @Column(name = "trafficApplyEnd", nullable = false)
    private Long trafficApplyEnd;

    @Column(name = "startType", nullable = false)
    private String startType;

    @Column(name = "greenOn", nullable = false)
    private Long greenOn;

    @Column(name = "redOn", nullable = false)
    private Long redOn;

    @Column(name = "setStartTime", nullable = false)
    private Long setStartTime;

    public static TrafficTimeEntity toTrafficTimeSaveEntity(TrafficTimeDTO trafficTimeDTO, TrafficEntity trafficEntity){
        TrafficTimeEntity trafficTimeEntity = new TrafficTimeEntity();
        trafficTimeEntity.setTrafficApplyStart(trafficTimeDTO.getTrafficApplyStart());
        trafficTimeEntity.setTrafficApplyEnd(trafficTimeDTO.getTrafficApplyEnd());
        trafficTimeEntity.setStartType(trafficTimeDTO.getStartType());
        trafficTimeEntity.setGreenOn(trafficTimeDTO.getGreenOn());
        trafficTimeEntity.setRedOn(trafficTimeDTO.getRedOn());
        trafficTimeEntity.setSetStartTime(trafficTimeDTO.getSetStartTime());
        trafficTimeEntity.setTrafficEntity(trafficEntity);
        return trafficTimeEntity;
    }

    public static TrafficTimeEntity toTrafficTimeUpdateEntity(TrafficTimeDTO trafficTimeDTO, TrafficEntity trafficEntity){
        TrafficTimeEntity trafficTimeEntity = new TrafficTimeEntity();
        trafficTimeEntity.setTrafficTimeId(trafficTimeDTO.getTrafficTimeId());
        trafficTimeEntity.setTrafficApplyStart(trafficTimeDTO.getTrafficApplyStart());
        trafficTimeEntity.setTrafficApplyEnd(trafficTimeDTO.getTrafficApplyEnd());
        trafficTimeEntity.setStartType(trafficTimeDTO.getStartType());
        trafficTimeEntity.setGreenOn(trafficTimeDTO.getGreenOn());
        trafficTimeEntity.setRedOn(trafficTimeDTO.getRedOn());
        trafficTimeEntity.setSetStartTime(trafficTimeDTO.getSetStartTime());
        trafficTimeEntity.setTrafficEntity(trafficEntity);
        return trafficTimeEntity;
    }
}
