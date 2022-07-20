package com.example.project.dto;

import com.example.project.entity.MyPlaceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPlaceDTO {
    private Long myPlaceId;
    private Long memberId;
    private String myPlaceName;
    private Double myPlaceLat;
    private Double myPlaceLon;
    private Long icon;
    private boolean bookmark;

    public static MyPlaceDTO toMyplaceDTO(MyPlaceEntity myPlaceEntity){
        MyPlaceDTO myPlaceDTO = new MyPlaceDTO();
        myPlaceDTO.setMyPlaceId(myPlaceEntity.getMyPlaceId());
        myPlaceDTO.setMemberId(myPlaceEntity.getMemberEntity().getMemberId());
        myPlaceDTO.setMyPlaceName(myPlaceEntity.getMyPlaceName());
        myPlaceDTO.setMyPlaceLat(myPlaceEntity.getMyPlaceLat());
        myPlaceDTO.setMyPlaceLon(myPlaceEntity.getMyPlaceLon());
        myPlaceDTO.setIcon(myPlaceEntity.getIcon());
        myPlaceDTO.setBookmark(myPlaceEntity.isBookmark());
        return myPlaceDTO;
    }
}
