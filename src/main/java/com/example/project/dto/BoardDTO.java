package com.example.project.dto;

import com.example.project.entity.BoardEntity;
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

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardId(boardEntity.getBoardId());
        boardDTO.setMemberId(boardEntity.getMemberEntity().getMemberId());
        boardDTO.setTrafficId(boardEntity.getTrafficEntity().getTrafficId());
        boardDTO.setBoardType(boardEntity.getBoardType());
        boardDTO.setBoardTypeLocation1(boardEntity.getBoardTypeLocation1());
        boardDTO.setBoardTypeLocation2(boardEntity.getBoardTypeLocation2());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardCreatedTime(boardEntity.getBoardCreatedTime());
        boardDTO.setBoardUpdateTime(boardEntity.getBoardUpdateTime());
        boardDTO.setBoardLike(boardEntity.getBoardLike());
        boardDTO.setBoardDislike(boardEntity.getBoardDislike());
        boardDTO.setManagerCheck(boardEntity.isManagerCheck());
        return boardDTO;
    }
}
