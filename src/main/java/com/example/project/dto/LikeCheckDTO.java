package com.example.project.dto;

import com.example.project.entity.LikeCheckEntity;
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

    public static LikeCheckDTO toLikeCheckDTO(LikeCheckEntity likeCheckEntity){
        LikeCheckDTO likeCheckDTO = new LikeCheckDTO();
        likeCheckDTO.setLikeCheckId(likeCheckEntity.getLikeCheckId());
        likeCheckDTO.setMemberId(likeCheckEntity.getMemberEntity().getMemberId());
        likeCheckDTO.setBoardId(likeCheckEntity.getBoardEntity().getBoardId());
        likeCheckDTO.setTrafficId(likeCheckEntity.getTrafficEntity().getTrafficId());
        likeCheckDTO.setLikeCheck(likeCheckEntity.isLikeCheck());
        return likeCheckDTO;
    }

    public static LikeCheckDTO toLikeBoardCheckDTO(LikeCheckEntity likeCheckEntity){
        LikeCheckDTO likeCheckDTO = new LikeCheckDTO();
        likeCheckDTO.setLikeCheckId(likeCheckEntity.getLikeCheckId());
        likeCheckDTO.setMemberId(likeCheckEntity.getMemberEntity().getMemberId());
        likeCheckDTO.setBoardId(likeCheckEntity.getBoardEntity().getBoardId());
        likeCheckDTO.setLikeCheck(likeCheckEntity.isLikeCheck());
        return likeCheckDTO;
    }
}
