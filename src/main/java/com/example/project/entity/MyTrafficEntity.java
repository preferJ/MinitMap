package com.example.project.entity;

import com.example.project.dto.MyTrafficDTO;
import com.example.project.dto.TrafficDTO;
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

    @Column(name = "myTrafficOrderNumber")
    private Double myTrafficOrderNumber;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "myTrafficEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TrafficTimeEntity> trafficTimeEntityList = new ArrayList<>();

    public static MyTrafficEntity toSaveMyTrafficEntity(MyTrafficDTO myTrafficDTO, MemberEntity memberEntity) {
        MyTrafficEntity myTrafficEntity = new MyTrafficEntity();
        myTrafficEntity.setMyTrafficId(myTrafficDTO.getMyTrafficId());
        myTrafficEntity.setMyTrafficLat(myTrafficDTO.getMyTrafficLat());
        myTrafficEntity.setMyTrafficLon(myTrafficDTO.getMyTrafficLon());
        myTrafficEntity.setMyTrafficName(myTrafficDTO.getMyTrafficName());
        myTrafficEntity.setMyTrafficOrderNumber(myTrafficDTO.getMyTrafficOrderNumber());
        myTrafficEntity.setMemberEntity(memberEntity);
        return myTrafficEntity;
    }
}
