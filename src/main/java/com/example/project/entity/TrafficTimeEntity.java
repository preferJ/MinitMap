package com.example.project.entity;

import com.example.project.dto.MyTrafficDTO;
import com.example.project.dto.Traffic2DTO;
import com.example.project.dto.Traffic3DTO;
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
    @JoinColumn(name = "trafficId")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myTrafficId")
    private MyTrafficEntity myTrafficEntity;

    public static TrafficTimeEntity toTrafficTimeSaveEntity(TrafficTimeDTO trafficTimeDTO, TrafficEntity trafficEntity) {
        TrafficTimeEntity trafficTimeEntity = new TrafficTimeEntity();
        trafficTimeEntity.setTrafficApplyStart(trafficTimeDTO.getTrafficApplyStart());
        trafficTimeEntity.setTrafficApplyEnd(trafficTimeDTO.getTrafficApplyEnd());
        trafficTimeEntity.setStartType(trafficTimeDTO.getStartType());
        trafficTimeEntity.setGreenOn(trafficTimeDTO.getGreenOn());
        trafficTimeEntity.setRedOn(trafficTimeDTO.getRedOn());
        trafficTimeEntity.setSetStartTime(trafficTimeDTO.getSetStartTime());
        trafficTimeEntity.setTrafficEntity(trafficEntity);
        trafficTimeEntity.setMyTrafficEntity(null);
        return trafficTimeEntity;
    }

    public static TrafficTimeEntity toTrafficTimeSaveEntity(TrafficTimeDTO trafficTimeDTO, MyTrafficEntity myTrafficEntity) {
        TrafficTimeEntity trafficTimeEntity = new TrafficTimeEntity();
        trafficTimeEntity.setTrafficApplyStart(trafficTimeDTO.getTrafficApplyStart());
        trafficTimeEntity.setTrafficApplyEnd(trafficTimeDTO.getTrafficApplyEnd());
        trafficTimeEntity.setStartType(trafficTimeDTO.getStartType());
        trafficTimeEntity.setGreenOn(trafficTimeDTO.getGreenOn());
        trafficTimeEntity.setRedOn(trafficTimeDTO.getRedOn());
        trafficTimeEntity.setSetStartTime(trafficTimeDTO.getSetStartTime());
        trafficTimeEntity.setMyTrafficEntity(myTrafficEntity);

        return trafficTimeEntity;
    }

    public static TrafficTimeEntity toTrafficTimeUpdateEntity(TrafficTimeDTO trafficTimeDTO, TrafficEntity trafficEntity) {
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

    public static TrafficTimeEntity myTrafficToTraffic(TrafficTimeEntity trafficTimeEntity, TrafficEntity trafficEntity) {
        TrafficTimeEntity trafficTimeEntity1 = new TrafficTimeEntity();
        trafficTimeEntity1.setTrafficApplyStart(trafficTimeEntity.getTrafficApplyStart());
        trafficTimeEntity1.setTrafficApplyEnd(trafficTimeEntity.getTrafficApplyEnd());
        trafficTimeEntity1.setStartType(trafficTimeEntity.getStartType());
        trafficTimeEntity1.setGreenOn(trafficTimeEntity.getGreenOn());
        trafficTimeEntity1.setRedOn(trafficTimeEntity.getRedOn());
        trafficTimeEntity1.setSetStartTime(trafficTimeEntity.getSetStartTime());
        trafficTimeEntity1.setTrafficEntity(trafficEntity);
        return trafficTimeEntity1;
    }

    public static TrafficTimeEntity trafficToMyTraffic(TrafficTimeEntity trafficTimeEntity, MyTrafficEntity myTrafficEntity) {
        TrafficTimeEntity trafficTimeEntity1 = new TrafficTimeEntity();
        trafficTimeEntity1.setTrafficApplyStart(trafficTimeEntity.getTrafficApplyStart());
        trafficTimeEntity1.setTrafficApplyEnd(trafficTimeEntity.getTrafficApplyEnd());
        trafficTimeEntity1.setStartType(trafficTimeEntity.getStartType());
        trafficTimeEntity1.setGreenOn(trafficTimeEntity.getGreenOn());
        trafficTimeEntity1.setRedOn(trafficTimeEntity.getRedOn());
        trafficTimeEntity1.setSetStartTime(trafficTimeEntity.getSetStartTime());
        trafficTimeEntity1.setMyTrafficEntity(myTrafficEntity);
        return trafficTimeEntity1;
    }

    public static TrafficTimeEntity adminSave2(Traffic2DTO trafficTimeDTO, TrafficEntity trafficEntity) {
        TrafficTimeEntity trafficTimeEntity = new TrafficTimeEntity();
        trafficTimeEntity.setTrafficApplyStart(trafficTimeDTO.getTrafficApplyStart2());
        trafficTimeEntity.setTrafficApplyEnd(trafficTimeDTO.getTrafficApplyEnd2());
        trafficTimeEntity.setStartType(trafficTimeDTO.getStartType2());
        trafficTimeEntity.setGreenOn(trafficTimeDTO.getGreenOn2());
        trafficTimeEntity.setRedOn(trafficTimeDTO.getRedOn2());
        trafficTimeEntity.setSetStartTime(trafficTimeDTO.getSetStartTime2());
        trafficTimeEntity.setTrafficEntity(trafficEntity);
        trafficTimeEntity.setMyTrafficEntity(null);
        return trafficTimeEntity;
    }
    public static TrafficTimeEntity adminSave3(Traffic3DTO trafficTimeDTO, TrafficEntity trafficEntity) {
        TrafficTimeEntity trafficTimeEntity = new TrafficTimeEntity();
        trafficTimeEntity.setTrafficApplyStart(trafficTimeDTO.getTrafficApplyStart3());
        trafficTimeEntity.setTrafficApplyEnd(trafficTimeDTO.getTrafficApplyEnd3());
        trafficTimeEntity.setStartType(trafficTimeDTO.getStartType3());
        trafficTimeEntity.setGreenOn(trafficTimeDTO.getGreenOn3());
        trafficTimeEntity.setRedOn(trafficTimeDTO.getRedOn3());
        trafficTimeEntity.setSetStartTime(trafficTimeDTO.getSetStartTime3());
        trafficTimeEntity.setTrafficEntity(trafficEntity);
        trafficTimeEntity.setMyTrafficEntity(null);
        return trafficTimeEntity;
    }
}
