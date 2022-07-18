package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminHistory {
    private Long adminHistoryId;
    private Long memberId;
    private String historyType;
    private String historyMessage;
    private LocalDateTime historyTime;
}
