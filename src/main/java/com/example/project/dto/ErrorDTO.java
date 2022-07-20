package com.example.project.dto;

import com.example.project.entity.ErrorEntity;
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

    public ErrorDTO toErrorDTO(ErrorEntity errorEntity){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorId(errorEntity.getErrorId());
        errorDTO.setTrafficId(errorEntity.getTrafficEntity().getTrafficId());
        errorDTO.setBoardId(errorEntity.getBoardEntity().getBoardId());
        errorDTO.setMemberId(errorEntity.getMemberEntity().getMemberId());
        errorDTO.setErrorType(errorEntity.getErrorType());
        errorDTO.setErrorTitle(errorEntity.getErrorTitle());
        errorDTO.setErrorContents(errorEntity.getErrorContents());
        errorDTO.setErrorCreatedTime(errorEntity.getErrorCreatedTime());
        errorDTO.setManagerCheck(errorEntity.isManagerCheck());
        return errorDTO;
    }
}
