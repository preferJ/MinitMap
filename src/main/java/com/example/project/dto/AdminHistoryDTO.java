package com.example.project.dto;

import com.example.project.entity.AdminHistoryEntity;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.ErrorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminHistoryDTO {
    private Long adminHistoryId;
    private Long memberId;
    private String historyType;
    private String historyMessage;
    private LocalDateTime historyTime;

    public static AdminHistoryDTO toAdminHistoryDTO(AdminHistoryEntity adminHistoryEntity){
        AdminHistoryDTO adminHistoryDTO = new AdminHistoryDTO();
        adminHistoryDTO.setAdminHistoryId(adminHistoryEntity.getAdminHistoryId());
        adminHistoryDTO.setMemberId(adminHistoryEntity.getMemberEntity().getMemberId());
        adminHistoryDTO.setHistoryType(adminHistoryEntity.getHistoryType());
        adminHistoryDTO.setHistoryMessage(adminHistoryEntity.getHistoryMessage());
        adminHistoryDTO.setHistoryTime(adminHistoryEntity.getHistoryTime());
        return adminHistoryDTO;
    }
}
