package com.example.project.controller;

import com.example.project.dto.*;
import com.example.project.service.AdminHistoryService;
import com.example.project.service.TrafficService;
import com.example.project.service.TrafficTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/traffic")
public class TrafficController {
    private final TrafficService trafficService;
    private final TrafficTimeService trafficTimeService;
    private final AdminHistoryService adminHistoryService;

    @GetMapping("adminSave")
    public String adminSave(@ModelAttribute TrafficDTO trafficDTO , @ModelAttribute TrafficTimeDTO trafficTimeDTO, @ModelAttribute Traffic2DTO traffic2DTO , @ModelAttribute Traffic3DTO traffic3DTO, HttpSession session){
        Long loginId = (Long) session.getAttribute("loginId");
        Long id = trafficService.adminSave(trafficDTO,loginId);
        trafficTimeService.adminSave(id,trafficTimeDTO,traffic2DTO,traffic3DTO);
        adminHistoryService.trafficSave(loginId,id);
        return "redirect:/board/adminTraffic";
    }

    @GetMapping("/adminList")
    public @ResponseBody List<TrafficDTO> adminList(HttpSession session){
        String email = (String) session.getAttribute("loginEmail");
        return trafficService.findByEmail(email);
    }

    @GetMapping("/adminLike")
    public @ResponseBody List<TrafficDTO> adminLike(HttpSession session , @RequestParam("num") Long num){
        String email = (String) session.getAttribute("loginEmail");
        return trafficService.adminLike(email,num);
    }

    @GetMapping("/adminDislike")
    public @ResponseBody List<TrafficDTO> adminDislike(HttpSession session, @RequestParam("num") Long num){
        String email = (String) session.getAttribute("loginEmail");
        return trafficService.adminDislike(email,num);
    }
}
