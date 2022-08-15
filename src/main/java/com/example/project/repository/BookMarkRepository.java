package com.example.project.repository;


import com.example.project.entity.BookMarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMarkEntity, Long> {

    List<BookMarkEntity> findAllByMemberId(Long memberId);


}
