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
    public static MyTrafficDTO toSaveMyTrafficDTO(MyTrafficEntity myTrafficEntity){
        MyTrafficDTO myTrafficDTO = new MyTrafficDTO();
        myTrafficDTO.setMyTrafficName(myTrafficEntity.getMyTrafficName());
        myTrafficDTO.setMyTrafficId(myTrafficEntity.getMyTrafficId());
        myTrafficDTO.setMemberId(myTrafficEntity.getMemberEntity().getMemberId());
        return myTrafficDTO;
    }
}
