package com.example.project.entity;

import com.example.project.dto.ErrorDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "error")
public class ErrorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "errorId")
    private Long errorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trafficId")
    private TrafficEntity trafficEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;


    @Column(name = "errorType", nullable = false)
    private String errorType;

    @Column(name = "errorTitle", nullable = false)
    private String errorTitle;

    @Column(name = "errorContents", nullable = false)
    private String errorContents;

    @CreationTimestamp
    @Column(name = "errorCreatedTime", updatable = false)
    private LocalDateTime errorCreatedTime;

    @Column(name = "managerCheck")
    private boolean managerCheck;

    public static ErrorEntity toBoardErrorSaveEntity(ErrorDTO errorDTO, BoardEntity boardEntity, MemberEntity memberEntity){
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setErrorType(errorDTO.getErrorType());
        errorEntity.setErrorTitle(errorDTO.getErrorTitle());
        errorEntity.setErrorContents(errorDTO.getErrorContents());
        errorEntity.setBoardEntity(boardEntity);
        errorEntity.setMemberEntity(memberEntity);
        return errorEntity;
    }

    public static ErrorEntity toTrafficErrorSaveEntity(ErrorDTO errorDTO,TrafficEntity trafficEntity, MemberEntity memberEntity){
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setErrorType(errorDTO.getErrorType());
        errorEntity.setErrorTitle(errorDTO.getErrorTitle());
        errorEntity.setErrorContents(errorDTO.getErrorContents());
        errorEntity.setTrafficEntity(trafficEntity);
        errorEntity.setMemberEntity(memberEntity);
        return errorEntity;
    }

    public static ErrorEntity toErrorUpdateEntity(ErrorDTO errorDTO,TrafficEntity trafficEntity, BoardEntity boardEntity, MemberEntity memberEntity){
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setErrorId(errorDTO.getErrorId());
        errorEntity.setErrorType(errorDTO.getErrorType());
        errorEntity.setErrorTitle(errorDTO.getErrorTitle());
        errorEntity.setErrorContents(errorDTO.getErrorContents());
        errorEntity.setManagerCheck(errorDTO.isManagerCheck());
        errorEntity.setTrafficEntity(trafficEntity);
        errorEntity.setBoardEntity(boardEntity);
        errorEntity.setMemberEntity(memberEntity);
        return errorEntity;
    }
}
