package com.example.project.entity;

import com.example.project.dto.MyTrafficDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "myTraffic")
public class MyTrafficEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "myTrafficId")
    private Long myTrafficId;

    @Column(name = "myTrafficName")
    private String myTrafficName;

    @Column(name = "myTrafficLat")
    private Double myTrafficLat;

    @Column(name = "myTrafficLon")
    private Double myTrafficLon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trafficId")
    private TrafficEntity trafficEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myTrafficId")
    private MyTrafficEntity myTrafficEntity;

    @OneToMany(mappedBy = "myTrafficEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TrafficTimeEntity> trafficTimeEntityList = new ArrayList<>();

    public static MyTrafficEntity toSaveMyTrafficEntity(String myTrafficName, MemberEntity memberEntity){
        MyTrafficEntity myTrafficEntity = new MyTrafficEntity();
        myTrafficEntity.setMyTrafficName(myTrafficName);
        myTrafficEntity.setMemberEntity(memberEntity);
        return myTrafficEntity;
    }
}
