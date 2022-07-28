package com.example.project.dto;

import com.example.project.entity.MyTrafficEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyTrafficDTO {
    Long myTrafficId;
    Long memberId;
    Long trafficId;
    String myTrafficName;
    public static MyTrafficDTO toSaveMyTrafficDTO(MyTrafficEntity myTrafficEntity){
        MyTrafficDTO myTrafficDTO = new MyTrafficDTO();
        myTrafficDTO.setMyTrafficName(myTrafficEntity.getMyTrafficName());
        myTrafficDTO.setMyTrafficId(myTrafficEntity.getMyTrafficId());
        myTrafficDTO.setTrafficId(myTrafficEntity.getTrafficEntity().getTrafficId());
        myTrafficDTO.setMemberId(myTrafficEntity.getMemberEntity().getMemberId());
        return myTrafficDTO;
    }
}
