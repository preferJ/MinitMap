package com.example.project.entity;

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
    @JoinColumn(name = "memberId" , nullable = false)
    private MemberEntity memberEntity;

    @Column(name = "errorType", nullable = false)
    private String errorType;

    @Column(name = "errorContents", nullable = false)
    private String errorContents;

    @CreationTimestamp
    @Column(name = "errorCreatedTime" , updatable = false)
    private LocalDateTime errorCreatedTime;

    @Column(name = "managerCheck")
    private boolean managerCheck;


}
