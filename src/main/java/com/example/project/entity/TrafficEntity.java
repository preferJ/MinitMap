package com.example.project.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "traffic")
public class TrafficEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trafficId")
    private Long trafficId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId" , nullable = false)
    private MemberEntity memberEntity;

    @Column(name = "trafficName" , nullable = false)
    private Long trafficName;

    @Column(name = "trafficLat" ,nullable = false)
    private Long trafficLat;

    @Column(name = "trafficLon" , nullable = false)
    private Long trafficLon;

    @Column(name = "trafficLike")
    @ColumnDefault("0") //default 0
    private Long trafficLike;

    @Column(name = "trafficDislike")
    @ColumnDefault("0") //default 0
    private Long trafficDislike;

    @OneToMany(mappedBy = "trafficEntity" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();


    @OneToOne(mappedBy = "trafficEntity" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private TrafficTimeEntity trafficTimeEntity = new TrafficTimeEntity();

    @OneToMany(mappedBy = "trafficEntity" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TrafficBookmarkEntity> trafficBookmarkEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "trafficEntity" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ErrorEntity> errorEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "trafficEntity" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeCheckEntity> likeCheckEntityList = new ArrayList<>();
}
