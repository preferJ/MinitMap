package com.example.project.entity;

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
    @JoinColumn(name = "memberId" , nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trafficId" , nullable = false)
    private TrafficEntity trafficEntity;
}
