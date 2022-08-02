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

    @OneToMany(mappedBy = "myTrafficEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TrafficTimeEntity> trafficTimeEntityList = new ArrayList<>();

    public static MyTrafficEntity toSaveMyTrafficEntity(Double lat,Double lon,String name, MemberEntity memberEntity){
        MyTrafficEntity myTrafficEntity = new MyTrafficEntity();
        myTrafficEntity.setMyTrafficLat(lat);
        myTrafficEntity.setMyTrafficLon(lon);
        myTrafficEntity.setMyTrafficName(name);
        myTrafficEntity.setMemberEntity(memberEntity);
        return myTrafficEntity;
    }
}
