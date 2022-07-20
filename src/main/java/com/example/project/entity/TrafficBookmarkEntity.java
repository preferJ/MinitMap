package com.example.project.entity;

import com.example.project.dto.TrafficBookmarkDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "trafficBookmark")
public class TrafficBookmarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trafficBookmarkId")
    private Long trafficBookmarkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trafficId", nullable = false)
    private TrafficEntity trafficEntity;

    public static TrafficBookmarkEntity toTrafficBookmarkSaveEntity(TrafficBookmarkDTO trafficBookmarkDTO, MemberEntity memberEntity, TrafficEntity trafficEntity){
        TrafficBookmarkEntity trafficBookmarkEntity = new TrafficBookmarkEntity();
        trafficBookmarkEntity.setMemberEntity(memberEntity);
        trafficBookmarkEntity.setTrafficEntity(trafficEntity);
        return trafficBookmarkEntity;
    }

    public static TrafficBookmarkEntity toTrafficBookmarkUpdateEntity(TrafficBookmarkDTO trafficBookmarkDTO, MemberEntity memberEntity, TrafficEntity trafficEntity){
        TrafficBookmarkEntity trafficBookmarkEntity = new TrafficBookmarkEntity();
        trafficBookmarkEntity.setTrafficBookmarkId(trafficBookmarkDTO.getTrafficId());
        trafficBookmarkEntity.setMemberEntity(memberEntity);
        trafficBookmarkEntity.setTrafficEntity(trafficEntity);
        return trafficBookmarkEntity;
    }
}
