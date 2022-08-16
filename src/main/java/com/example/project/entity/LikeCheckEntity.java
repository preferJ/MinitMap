package com.example.project.entity;

import com.example.project.dto.LikeCheckDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "likeCheck")
public class LikeCheckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeCheckId")
    private Long likeCheckId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trafficId")
    private TrafficEntity trafficEntity;

    @Column(name = "likeCheck")
    private boolean likeCheck;


    public static LikeCheckEntity toLikeCheckSaveEntity(boolean check, MemberEntity memberEntity, BoardEntity boardEntity){
        LikeCheckEntity likeCheckEntity = new LikeCheckEntity();
        likeCheckEntity.setLikeCheck(check);
        likeCheckEntity.setMemberEntity(memberEntity);
        likeCheckEntity.setBoardEntity(boardEntity);
        return likeCheckEntity;
    }

    public static LikeCheckEntity toLikeCheckTrafficSaveEntity(boolean check, MemberEntity memberEntity, TrafficEntity trafficEntity){
        LikeCheckEntity likeCheckEntity = new LikeCheckEntity();
        likeCheckEntity.setLikeCheck(check);
        likeCheckEntity.setMemberEntity(memberEntity);
        likeCheckEntity.setTrafficEntity(trafficEntity);
        return likeCheckEntity;
    }
    public static LikeCheckEntity toLikeCheckSaveEntity1(LikeCheckDTO likeCheckDTO, MemberEntity memberEntity, BoardEntity boardEntity, TrafficEntity trafficEntity){
        LikeCheckEntity likeCheckEntity = new LikeCheckEntity();
        likeCheckEntity.setLikeCheck(likeCheckDTO.isLikeCheck());
        likeCheckEntity.setMemberEntity(memberEntity);
        likeCheckEntity.setBoardEntity(boardEntity);
        likeCheckEntity.setTrafficEntity(trafficEntity);
        return likeCheckEntity;
    }

    public static LikeCheckEntity toLikeCheckUpdateEntity(LikeCheckDTO likeCheckDTO, MemberEntity memberEntity, BoardEntity boardEntity, TrafficEntity trafficEntity){
        LikeCheckEntity likeCheckEntity = new LikeCheckEntity();
        likeCheckEntity.setLikeCheckId(likeCheckDTO.getLikeCheckId());
        likeCheckEntity.setLikeCheck(likeCheckDTO.isLikeCheck());
        likeCheckEntity.setMemberEntity(memberEntity);
        likeCheckEntity.setBoardEntity(boardEntity);
        likeCheckEntity.setTrafficEntity(trafficEntity);
        return likeCheckEntity;
    }
}
