package com.example.project.controller;

import com.example.project.dto.BookMarkDTO;
import com.example.project.service.BookMarkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/bookMark")
public class BookMarkController {
    private final BookMarkService bookMarkService;

    @PostMapping("/list")
    @ResponseBody
    public List<BookMarkDTO> findAll(HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginId");
        List<BookMarkDTO> bookMarkDTOList = bookMarkService.findAll(memberId);
        return bookMarkDTOList;
    }


    @PostMapping("/save")
    @ResponseBody
    public void save(@RequestParam("trafficId") Long trafficId,
                     @RequestParam("ck") Long ck, HttpSession session) {
        System.out.println("BookMarkController.save");
        Long memberId = (Long) session.getAttribute("loginId");
        System.out.println(memberId);

        if (memberId != null) {
            BookMarkDTO bookMarkDTO = new BookMarkDTO();
            bookMarkDTO.setMemberId(memberId);

            if (ck == 1) {
                bookMarkDTO.setTrafficId(trafficId);
            } else {
                bookMarkDTO.setMyTrafficId(trafficId);
            }
            bookMarkService.save(bookMarkDTO);

            List<BookMarkDTO> bookMarkDTOList = bookMarkService.findAll(memberId);
            System.out.println(bookMarkDTOList);
        }
    }

    @PostMapping("/delete")
    public void delete(@ModelAttribute BookMarkDTO bookMarkDTO) {
        bookMarkService.delete(bookMarkDTO);
    }

}
