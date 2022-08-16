package com.example.project.service;

import com.example.project.dto.BookMarkDTO;
import com.example.project.dto.MyPlaceDTO;
import com.example.project.entity.BookMarkEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyPlaceEntity;
import com.example.project.repository.BookMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;

    public void save(BookMarkDTO bookMarkDTO) {
        boolean dupCheck = true;

        List<BookMarkEntity> bookMarkEntityList = bookMarkRepository.findAllByMemberId(bookMarkDTO.getMemberId());
        for (BookMarkEntity bookMarkEntity : bookMarkEntityList) {
            if (bookMarkEntity.getTrafficId() == bookMarkDTO.getTrafficId()) {
                dupCheck = false;
            }
            if (bookMarkEntity.getMyTrafficId() == bookMarkDTO.getMyTrafficId()) {
                dupCheck = false;
            }
        }

        if (dupCheck) {
            bookMarkRepository.save(BookMarkEntity.toBookMarkWithTrafficSaveEntity(bookMarkDTO));
        }

    }

    public void delete(BookMarkDTO bookMarkDTO) {
        bookMarkRepository.deleteById(bookMarkDTO.getBookMarkId());
    }

    public List<BookMarkDTO> findAll(Long memberId) {
        List<BookMarkEntity> bookMarkEntityList = bookMarkRepository.findAllByMemberId(memberId);
        List<BookMarkDTO> bookMarkDTOList = new ArrayList<>();
        for (BookMarkEntity bookMarkEntity : bookMarkEntityList) {
            if (bookMarkEntity.getMemberId() == memberId) {
                bookMarkDTOList.add(BookMarkDTO.toBookMarkDTO(bookMarkEntity));
            }
        }
        return bookMarkDTOList;
    }


}
