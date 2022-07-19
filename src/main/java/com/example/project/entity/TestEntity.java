package com.example.project.entity;

import com.example.project.dto.TestDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "test123")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trafficTimeId")
    private Long trafficTimeId;

    @Column(name = "trafficId")
    private Long trafficId;

    @Column(name = "trafficApplyStart")
    private LocalTime trafficApplyStart;

    @Column(name = "trafficApplyEnd")
    private LocalTime trafficApplyEnd;

    @Column(name = "startType")
    private String startType;

    @Column(name = "greenOn")
    private LocalTime greenOn;

    @Column(name = "redOn", nullable = false)
    private LocalTime redOn;

    @Column(name = "setStartTime", nullable = false)
    private LocalTime setStartTime;

    public static TestEntity toEntity(TestDTO testDTO) {
        TestEntity testEntity = new TestEntity();
        testEntity.setStartType(testDTO.getStartType());
        testEntity.setSetStartTime(testDTO.getSetStartTime());
        testEntity.setTrafficApplyStart(testDTO.getTrafficApplyStart());
        testEntity.setTrafficApplyEnd(testDTO.getTrafficApplyEnd());
        return testEntity;
    }
}
