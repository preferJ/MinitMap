package com.example.project.dto;

import com.example.project.entity.MyTrafficEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyTrafficDTO {
    private Long myTrafficId;
    private Long memberId;
    private String myTrafficName;
    private Double myTrafficLat;
    private Double myTrafficLon;
    private Long myTrafficOrderNumber;
    //ㅅㅎ 수정 신호등순서필드 추가
    private Long boardId;


    // ㅅㅎ수정 soSave메서드에 오더넘버 추가
    public static MyTrafficDTO toSaveMyTrafficDTO(MyTrafficEntity myTrafficEntity){
        MyTrafficDTO myTrafficDTO = new MyTrafficDTO();
        myTrafficDTO.setMyTrafficId(myTrafficEntity.getMyTrafficId());
        myTrafficDTO.setMyTrafficName(myTrafficEntity.getMyTrafficName());
        myTrafficDTO.setMemberId(myTrafficEntity.getMemberEntity().getMemberId());
        myTrafficDTO.setMyTrafficLat(myTrafficEntity.getMyTrafficLat());
        myTrafficDTO.setMyTrafficLon(myTrafficEntity.getMyTrafficLon());
        myTrafficDTO.setMyTrafficOrderNumber(myTrafficEntity.getMyTrafficOrderNumber());
        myTrafficDTO.setBoardId(myTrafficEntity.getBoardId());
        return myTrafficDTO;
    }
}
