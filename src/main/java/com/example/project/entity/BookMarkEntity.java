package com.example.project.entity;

import com.example.project.dto.AdminHistoryDTO;
import com.example.project.dto.BookMarkDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "bookMark")
public class BookMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookMarkId")
    private Long bookMarkId;

    @Column(name = "memberId")
    private Long memberId;

    @Column(name = "trafficId")
    private Long trafficId;

    @Column(name = "myTrafficId")
    private Long myTrafficId;


    public static BookMarkEntity toBookMarkWithTrafficSaveEntity(BookMarkDTO bookMarkDTO) {
        BookMarkEntity bookMarkEntity = new BookMarkEntity();
        bookMarkEntity.setBookMarkId(bookMarkDTO.getBookMarkId());
        bookMarkEntity.setTrafficId(bookMarkDTO.getTrafficId());
        bookMarkEntity.setMyTrafficId(bookMarkDTO.getMyTrafficId());
        bookMarkEntity.setMemberId(bookMarkDTO.getMemberId());
        return bookMarkEntity;
    }


}
