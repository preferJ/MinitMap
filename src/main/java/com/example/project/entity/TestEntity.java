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
    private Long trafficApplyStart;

    @Column(name = "trafficApplyEnd")
    private Long trafficApplyEnd;

    @Column(name = "startType")
    private String startType;

    @Column(name = "greenOn")
    private Long greenOn;

    @Column(name = "redOn")
    private Long redOn;

    @Column(name = "setStartTime", nullable = false)
    private Long setStartTime;

    public static TestEntity toEntity(TestDTO testDTO) {
        TestEntity testEntity = new TestEntity();
        testEntity.setStartType(testDTO.getStartType());
        testEntity.setSetStartTime(testDTO.getSetStartTime());
        testEntity.setTrafficApplyStart(testDTO.getTrafficApplyStart());
        testEntity.setTrafficApplyEnd(testDTO.getTrafficApplyEnd());
        return testEntity;
    }
}
