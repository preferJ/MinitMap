package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long boardId;
    private Long memberId;
    private Long trafficId;
    private String boardType;
    private String boardTypeLocation1;
    private String boardTypeLocation2;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdateTime;
    private Long boardLike;
    private Long boardDislike;
    private boolean managerCheck;
}
