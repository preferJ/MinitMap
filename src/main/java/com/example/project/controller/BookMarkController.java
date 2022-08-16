package com.example.project.controller;

import com.example.project.dto.BookMarkDTO;
import com.example.project.dto.TrafficBookmarkDTO;
import com.example.project.service.BookMarkService;
import com.example.project.service.MyTrafficService;
import com.example.project.service.TrafficService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/bookMark")
public class BookMarkController {
    private final BookMarkService bookMarkService;

    private final MyTrafficService myTrafficService;

    private final TrafficService trafficService;

    @PostMapping("/list")
    @ResponseBody
    public List<TrafficBookmarkDTO> getList(HttpSession session) {

        Long memberId = (Long) session.getAttribute("loginId");
        List<BookMarkDTO> bookMarkDTOList = bookMarkService.findAll(memberId);
        List<TrafficBookmarkDTO> trafficBookmarkDTOList = new ArrayList<>();

        for (BookMarkDTO bookMarkDTO : bookMarkDTOList){
            Long id = 1L;
            if (bookMarkDTO.getTrafficId() == null){
                id = bookMarkDTO.getMyTrafficId();
            }else {
                id = bookMarkDTO.getTrafficId();
            }


            if (bookMarkDTO.getTrafficId() == null){
                TrafficBookmarkDTO trafficBookmarkDTO = new TrafficBookmarkDTO();
                trafficBookmarkDTO.setTrafficBookmarkId(bookMarkDTO.getBookMarkId());
                trafficBookmarkDTO.setMyTrafficId(bookMarkDTO.getMyTrafficId());
                trafficBookmarkDTO.setMemberId(memberId);
                trafficBookmarkDTO.setTrafficName(myTrafficService.findByIdName(id));
                trafficBookmarkDTO.setLat(myTrafficService.findByIdLat(id));
                trafficBookmarkDTO.setLon(myTrafficService.findByIdLon(id));
                trafficBookmarkDTOList.add(trafficBookmarkDTO);

            }else{
                TrafficBookmarkDTO trafficBookmarkDTO = new TrafficBookmarkDTO();
                trafficBookmarkDTO.setTrafficBookmarkId(bookMarkDTO.getBookMarkId());
                trafficBookmarkDTO.setTrafficId(bookMarkDTO.getTrafficId());
                trafficBookmarkDTO.setMemberId(memberId);
                trafficBookmarkDTO.setTrafficName(trafficService.findByIdName(id));
                trafficBookmarkDTO.setLat(trafficService.findByIdLat(id));
                trafficBookmarkDTO.setLon(trafficService.findByIdLon(id));
                trafficBookmarkDTOList.add(trafficBookmarkDTO);
            }
        }



        return trafficBookmarkDTOList;
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
