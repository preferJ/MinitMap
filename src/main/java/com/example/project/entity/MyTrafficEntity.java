package com.example.project.entity;

import com.example.project.dto.MyTrafficDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trafficId")
    private TrafficEntity trafficEntity;

    public static MyTrafficEntity toSaveMyTrafficEntity(String myTrafficName, MemberEntity memberEntity, TrafficEntity trafficEntity){
        MyTrafficEntity myTrafficEntity = new MyTrafficEntity();
        myTrafficEntity.setMyTrafficName(myTrafficName);
        myTrafficEntity.setMemberEntity(memberEntity);
        myTrafficEntity.setTrafficEntity(trafficEntity);
        return myTrafficEntity;
    }
}
