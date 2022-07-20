package com.example.project.entity;

import com.example.project.dto.MyPlaceDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "myPlace")
public class MyPlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "myPlaceId")
    private Long myPlaceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;

    @Column(name = "myPlaceName", nullable = false)
    private String myPlaceName;

    @Column(name = "myPlaceLat", nullable = false)
    private Double myPlaceLat;

    @Column(name = "myPlaceLon", nullable = false)
    private Double myPlaceLon;

    @Column(name = "icon", nullable = false)
    @ColumnDefault("0") //default 0
    private Long icon;

    @Column(name = "bookmark")
    @ColumnDefault("0") //default 0
    private boolean bookmark;

    public static MyPlaceEntity toMyPlaceSaveEntity(MyPlaceDTO myPlaceDTO, MemberEntity memberEntity){
        MyPlaceEntity myPlaceEntity = new MyPlaceEntity();
        myPlaceEntity.setMyPlaceName(myPlaceDTO.getMyPlaceName());
        myPlaceEntity.setMyPlaceLat(myPlaceDTO.getMyPlaceLat());
        myPlaceEntity.setMyPlaceLon(myPlaceDTO.getMyPlaceLon());
        myPlaceEntity.setIcon(myPlaceDTO.getIcon());
        myPlaceEntity.setMemberEntity(memberEntity);
        return myPlaceEntity;
    }

    public static MyPlaceEntity toMyPlaceUpdateEntity(MyPlaceDTO myPlaceDTO, MemberEntity memberEntity){
        MyPlaceEntity myPlaceEntity = new MyPlaceEntity();
        myPlaceEntity.setMyPlaceId(myPlaceDTO.getMyPlaceId());
        myPlaceEntity.setMyPlaceName(myPlaceDTO.getMyPlaceName());
        myPlaceEntity.setMyPlaceLat(myPlaceDTO.getMyPlaceLat());
        myPlaceEntity.setMyPlaceLon(myPlaceDTO.getMyPlaceLon());
        myPlaceEntity.setIcon(myPlaceDTO.getIcon());
        myPlaceEntity.setBookmark(myPlaceDTO.isBookmark());
        myPlaceEntity.setMemberEntity(memberEntity);
        return myPlaceEntity;
    }
}
