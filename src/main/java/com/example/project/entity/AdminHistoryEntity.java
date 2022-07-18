package com.example.project.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "adminHistory")
public class AdminHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminHistoryId")
    private Long adminHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId" , nullable = false)
    private MemberEntity memberEntity;

    @Column(name = "historyType" , nullable = false)
    private String historyType;

    @Column(name = "historyMessage" , nullable = false)
    private String historyMessage;

    @CreationTimestamp
    @Column(name = "historyTime" , updatable = false)
    private LocalDateTime historyTime;


}
