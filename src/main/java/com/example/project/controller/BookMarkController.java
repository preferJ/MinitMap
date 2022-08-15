package com.example.project.controller;

import com.example.project.dto.BookMarkDTO;
import com.example.project.service.BookMarkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/bookMark")
public class BookMarkController {
    private final BookMarkService bookMarkService;

    @PostMapping("/list")
    public List<BookMarkDTO> findAll(HttpSession session){
        Long memberId = (Long) session.getAttribute("loginId");
        List<BookMarkDTO> bookMarkDTOList = bookMarkService.findAll(memberId);
        return bookMarkDTOList;


    }


    @PostMapping("/save")
    public void save(@ModelAttribute BookMarkDTO bookMarkDTO) {
         bookMarkService.save(bookMarkDTO);
    }

    @PostMapping("/delete")
    public void delete(@ModelAttribute BookMarkDTO bookMarkDTO) {
        bookMarkService.delete(bookMarkDTO);
    }

}
