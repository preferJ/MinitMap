package com.example.project.entity;

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
    @JoinColumn(name = "memberId" , nullable = false)
    private MemberEntity memberEntity;

    @Column(name = "startPoint" , nullable = false)
    private String stratPoint;

    @Column(name = "startPointLat" , nullable = false)
    private Double startPointLat;

    @Column(name = "startPointLon" , nullable = false)
    private Double startPointLon;

    @Column(name = "destination" , nullable = false)
    private String destination;

    @Column(name = "endPointLat",nullable = false)
    private Double endPointLat;

    @Column(name = "endPointLon",nullable = false)
    private Double endPointLon;
}
