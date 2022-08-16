package com.example.project.dto;

import com.example.project.entity.AdminHistoryEntity;
import com.example.project.entity.BookMarkEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkDTO {

    private Long bookMarkId;
    private Long memberId;
    private Long trafficId;
    private Long myTrafficId;


    public static BookMarkDTO toBookMarkDTO(BookMarkEntity bookMarkEntity) {
        BookMarkDTO bookMarkDTO = new BookMarkDTO();
        bookMarkDTO.setBookMarkId(bookMarkEntity.getBookMarkId());
        bookMarkDTO.setMemberId(bookMarkEntity.getMemberId());
        bookMarkDTO.setMyTrafficId(bookMarkEntity.getMyTrafficId());
        bookMarkDTO.setTrafficId(bookMarkEntity.getTrafficId());
        return bookMarkDTO;
    }


}
