package com.example.project.entity;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trafficId", nullable = false)
    private TrafficEntity trafficEntity;

    @Column(name = "trafficTime", nullable = false)
    private Long trafficId;

    @Column(name = "trafficApplyStart", nullable = false)
    private LocalTime trafficApplyStart;

    @Column(name = "trafficApplyEnd", nullable = false)
    private LocalTime trafficApplyEnd;

    @Column(name = "startType", nullable = false)
    private String startType;

    @Column(name = "greenOn", nullable = false)
    private LocalTime greenOn;

    @Column(name = "redOn", nullable = false)
    private LocalTime redOn;

    @Column(name = "setStartTime", nullable = false)
    private LocalTime setStartTime;
}
