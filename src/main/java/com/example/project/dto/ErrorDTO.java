package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private Long errorId;
    private Long trafficId;
    private Long boardId;
    private Long memberId;
    private String errorType;
    private String errorTitle;
    private String errorContents;
    private LocalDateTime errorCreatedTime;
    private boolean managerCheck;
}
