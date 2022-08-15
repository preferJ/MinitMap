package com.example.project.dto;

import com.example.project.entity.BookMarkEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkAndTrafficDTO {

    private Long bookMarkId;
    private Long memberId;
    private Long trafficId;
    private Long myTrafficId;


public static BookMarkAndTrafficDTO toBookMarkDTO(BookMarkEntity bookMarkEntity, TrafficDTO trafficDTO){
    BookMarkAndTrafficDTO bookMarkDTO = new BookMarkAndTrafficDTO();
    bookMarkDTO.setBookMarkId(bookMarkEntity.getBookMarkId());
    bookMarkDTO.setMemberId(bookMarkEntity.getMemberId());
    bookMarkDTO.setMyTrafficId(bookMarkEntity.getMyTrafficId());
    bookMarkDTO.setTrafficId(bookMarkEntity.getTrafficId());
return bookMarkDTO;
}


}
