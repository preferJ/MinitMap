package com.example.project.entity;

import com.example.project.dto.TrafficDTO;
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
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;

    @Column(name = "trafficLat", nullable = false)
    private Double trafficLat;

    @Column(name = "trafficLon", nullable = false)
    private Double trafficLon;

    @Column(name = "trafficLike")
    @ColumnDefault("0") //default 0
    private Long trafficLike;

    @Column(name = "trafficDislike")
    @ColumnDefault("0") //default 0
    private Long trafficDislike;

    @OneToMany(mappedBy = "trafficEntity", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "trafficEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TrafficTimeEntity> trafficTimeEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "trafficEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TrafficBookmarkEntity> trafficBookmarkEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "trafficEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ErrorEntity> errorEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "trafficEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeCheckEntity> likeCheckEntityList = new ArrayList<>();


    public static TrafficEntity trafficSaveEntity(TrafficDTO trafficDTO, MemberEntity memberEntity){
        TrafficEntity trafficEntity = new TrafficEntity();
        trafficEntity.setTrafficLat(trafficDTO.getTrafficLat());
        trafficEntity.setTrafficLon(trafficDTO.getTrafficLon());
        trafficEntity.setTrafficLike(0l);
        trafficEntity.setTrafficDislike(0l);
        trafficEntity.setMemberEntity(memberEntity);
        return trafficEntity;
    }
    public static TrafficEntity trafficUpdateEntity(TrafficDTO trafficDTO, MemberEntity memberEntity){
        TrafficEntity trafficEntity = new TrafficEntity();
        trafficEntity.setTrafficLat(trafficDTO.getTrafficLat());
        trafficEntity.setTrafficLon(trafficDTO.getTrafficLon());
        trafficEntity.setTrafficLike(trafficDTO.getTrafficLike());
        trafficEntity.setTrafficDislike(trafficDTO.getTrafficDisLike());
        trafficEntity.setMemberEntity(memberEntity);
        return trafficEntity;
    }

    public static TrafficEntity myTrafficToTraffic(MemberEntity memberEntity, Double lat , Double lon){
        TrafficEntity trafficEntity = new TrafficEntity();
        trafficEntity.setMemberEntity(memberEntity);
        trafficEntity.setTrafficLat(lat);
        trafficEntity.setTrafficLon(lon);

        return trafficEntity;
    }
}
