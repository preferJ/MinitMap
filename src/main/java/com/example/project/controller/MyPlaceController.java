package com.example.project.controller;

import com.example.project.dto.MyPlaceDTO;
import com.example.project.service.MyPlaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/myPlace")
public class MyPlaceController {
    private final MyPlaceService myPlaceService;

    @GetMapping("/save")
    public String save(@RequestParam("myPlaceLat") Double myPlaceLat,
                       @RequestParam("myPlaceLng") Double myPlaceLng,
                       @RequestParam("myPlaceName") String myPlaceName,
                       @RequestParam(value = "icon",required = false,defaultValue = "0") Long icon,
                       HttpSession session){
        Long id = (Long) session.getAttribute("loginId");
        MyPlaceDTO myPlaceDTO = new MyPlaceDTO(id,myPlaceName,myPlaceLat,myPlaceLng,icon);
        myPlaceService.save(myPlaceDTO);
        return "redirect:/1tapTest";
    }
}
