package com.example.project.controller;

import com.example.project.dto.TrafficTimeDTO;
import com.example.project.service.TrafficTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trafficTime")
public class TrafficTimeController {
    private final TrafficTimeService trafficTimeService;

    @GetMapping("/")
    public String trafficTImeForm(@RequestParam("id") Long id , Model model){
        model.addAttribute("id",id);
        return "/myTraffic/myTrafficTime";
    }

    @GetMapping("/list")
    public @ResponseBody List<TrafficTimeDTO>  trafficTImeList(@RequestParam("id") Long id){
        List<TrafficTimeDTO> trafficTimeDTOS = trafficTimeService.findByMyTrafficId(id);
        return trafficTimeDTOS;
    }
}
