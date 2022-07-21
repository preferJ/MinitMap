package com.example.project.entity;

import com.example.project.dto.BoardDTO;
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
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trafficId")
    private TrafficEntity trafficEntity;

    @Column(name = "boardType", nullable = false)
    private String boardType;

    @Column(name = "boardTypeLocation1")
    private String boardTypeLocation1;

    @Column(name = "boardTypeLocation2")
    private String boardTypeLocation2;

    @Column(name = "boardTitle", nullable = false)
    private String boardTitle;

    @Column(name = "boardContents", nullable = false)
    private String boardContents;

    @Column(name = "boardHits")
    private Long boardHits;

    @CreationTimestamp
    @Column(name = "boardCreatedTime", updatable = false)
    private LocalDateTime boardCreatedTime;

    @UpdateTimestamp
    @Column(name = "boardUpdatedTime", insertable = false)
    private LocalDateTime boardUpdateTime;

    @Column(name = "boardLike")
    private Long boardLike;

    @Column(name = "boardDislike")
    private Long boardDislike;

    @Column(name = "managerCheck")
    private boolean managerCheck;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ErrorEntity> errorEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeCheckEntity> likeCheckEntityList = new ArrayList<>();

    public static BoardEntity toBoardTrafficSaveEntity(BoardDTO boardDTO, MemberEntity memberEntity, TrafficEntity trafficEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardType(boardDTO.getBoardType());
        boardEntity.setBoardTypeLocation1(boardDTO.getBoardTypeLocation1());
        boardEntity.setBoardTypeLocation2(boardDTO.getBoardTypeLocation2());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setMemberEntity(memberEntity);
        boardEntity.setTrafficEntity(trafficEntity);
        boardEntity.setBoardHits(0l);
        boardEntity.setBoardLike(0l);
        boardEntity.setBoardDislike(0l);
        boardEntity.setManagerCheck(false);
        return boardEntity;
    }

    public static BoardEntity toBoardSaveEntity(BoardDTO boardDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardType(boardDTO.getBoardType());
        boardEntity.setBoardTypeLocation1(boardDTO.getBoardTypeLocation1());
        boardEntity.setBoardTypeLocation2(boardDTO.getBoardTypeLocation2());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setMemberEntity(memberEntity);
        boardEntity.setBoardHits(0l);
        boardEntity.setBoardLike(0l);
        boardEntity.setBoardDislike(0l);
        boardEntity.setManagerCheck(false);
        return boardEntity;
    }

    public static BoardEntity toBoardUpdateEntity(BoardDTO boardDTO, MemberEntity memberEntity, TrafficEntity trafficEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardId(boardDTO.getBoardId());
        boardEntity.setBoardType(boardDTO.getBoardType());
        boardEntity.setBoardTypeLocation1(boardDTO.getBoardTypeLocation1());
        boardEntity.setBoardTypeLocation2(boardDTO.getBoardTypeLocation2());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardLike(boardDTO.getBoardLike());
        boardEntity.setBoardDislike(boardDTO.getBoardDislike());
        boardEntity.setManagerCheck(boardDTO.isManagerCheck());
        boardEntity.setMemberEntity(memberEntity);
        boardEntity.setTrafficEntity(trafficEntity);
        return boardEntity;
    }
}
