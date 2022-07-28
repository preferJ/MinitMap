package com.example.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "myTraffoc")
public class MyTrafficEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "myTrafficId")
    private Long myTrafficId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trafficId")
    private TrafficEntity trafficEntity;

    public static MyTrafficEntity toSaveMyTrafficEntity(MemberEntity memberEntity,TrafficEntity trafficEntity){
        MyTrafficEntity myTrafficEntity = new MyTrafficEntity();
        myTrafficEntity.setMemberEntity(memberEntity);
        myTrafficEntity.setTrafficEntity(trafficEntity);
        return myTrafficEntity;
    }
}
