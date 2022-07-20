package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeCheckDTO {
    private Long likeCheckId;
    private Long memberId;
    private Long boardId;
    private Long trafficId;
    private boolean likeCheck;
}
