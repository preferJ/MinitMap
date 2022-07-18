package com.example.project.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardId")
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId" , nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trafficId")
    private TrafficEntity trafficEntity;

    @Column(name = "boardType", nullable = false)
    private Long boardType;

    @Column(name = "boardTypeLocation1")
    private String boardTypeLocation1;

    @Column(name = "boardTypeLocation2")
    private String boardTypeLocation2;

    @Column(name = "boardTitle", nullable = false)
    private String boardTitle;

    @Column(name = "boardContents", nullable = false)
    private String boardContents;

    @Column(name = "boardHits")
    @ColumnDefault("0") //default 0
    private Long boardHits;

    @CreationTimestamp
    @Column(name = "boardCreatedTime",updatable = false)
    private LocalDateTime boardCreatedTime;

    @UpdateTimestamp
    @Column(name = "boardUpdatedTime",insertable = false)
    private LocalDateTime boardUpdateTime;

    @Column(name = "boardLike")
    @ColumnDefault("0") //default 0
    private Long boardLike;

    @Column(name = "boardDislike")
    @ColumnDefault("0") //default 0
    private Long boardDislike;

    @Column(name = "managerCheck")
    @ColumnDefault("0") //default 0
    private boolean managerCheck;

    @OneToMany(mappedBy = "boardEntity" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ErrorEntity> errorEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeCheckEntity> likeCheckEntityList = new ArrayList<>();
}
