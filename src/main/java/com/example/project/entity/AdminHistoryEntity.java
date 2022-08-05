package com.example.project.entity;

import com.example.project.dto.AdminHistoryDTO;
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
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;

    @Column(name = "historyType", nullable = false)
    private String historyType;

    @Column(name = "historyMessage", nullable = false)
    private String historyMessage;

    @CreationTimestamp
    @Column(name = "historyTime", updatable = false)
    private LocalDateTime historyTime;

    public static AdminHistoryEntity toAdminHistorySaveEntity(AdminHistoryDTO adminHistoryDTO, MemberEntity memberEntity){
        AdminHistoryEntity adminHistoryEntity = new AdminHistoryEntity();
        adminHistoryEntity.setHistoryType(adminHistoryDTO.getHistoryType());
        adminHistoryEntity.setHistoryMessage(adminHistoryDTO.getHistoryMessage());
        adminHistoryEntity.setHistoryTime(adminHistoryDTO.getHistoryTime());
        adminHistoryEntity.setMemberEntity(memberEntity);
        return adminHistoryEntity;
    }

    public static AdminHistoryEntity toAdminHistoryUpdateEntity(AdminHistoryDTO adminHistoryDTO,MemberEntity memberEntity){
        AdminHistoryEntity adminHistoryEntity = new AdminHistoryEntity();
        adminHistoryEntity.setAdminHistoryId(adminHistoryDTO.getAdminHistoryId());
        adminHistoryEntity.setHistoryType(adminHistoryDTO.getHistoryType());
        adminHistoryEntity.setHistoryMessage(adminHistoryDTO.getHistoryMessage());
        adminHistoryEntity.setHistoryTime(adminHistoryDTO.getHistoryTime());
        adminHistoryEntity.setMemberEntity(memberEntity);
        return adminHistoryEntity;
    }
}
