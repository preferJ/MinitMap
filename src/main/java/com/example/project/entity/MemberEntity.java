package com.example.project.entity;

import com.example.project.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DynamicInsert
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long memberId;

    @Column(name = "memberEmail", unique = true, nullable = false)
    private String memberEmail;

    @Column(name = "memberPassword", nullable = false)
    private String memberPassword;

    @Column(name = "memberNickname", nullable = false, unique = true)
    private String memberNickname;

    @Column(name = "memberPhone", nullable = false, unique = true)
    private String memberPhone;

    @Column(name = "memberLevel")
    @ColumnDefault("1") //default 1
    private Long memberLevel;

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MyPlaceEntity> myPlaceEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RutinEntity> rutinEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TrafficEntity> trafficEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TrafficBookmarkEntity> trafficBookmarkEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ErrorEntity> errorEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<AdminHistoryEntity> adminHistoryEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeCheckEntity> likeCheckEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MyTrafficEntity> myTrafficEntityList = new ArrayList<>();

    public static MemberEntity toMemberSaveEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberNickname(memberDTO.getMemberNickname());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        return memberEntity;
    }
    public static MemberEntity toMemberUpdateEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberNickname(memberDTO.getMemberNickname());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setMemberLevel(memberDTO.getMemberLevel());
        return memberEntity;
    }
}
