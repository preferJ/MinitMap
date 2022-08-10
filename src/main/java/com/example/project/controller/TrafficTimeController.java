package com.example.project.controller;

import com.example.project.dto.TrafficTimeDTO;
import com.example.project.service.TrafficTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trafficTime")
public class TrafficTimeController {
    private final TrafficTimeService trafficTimeService;

    @GetMapping("/")
    public String trafficTImeForm(@RequestParam("id") Long id , Model model){
        List<TrafficTimeDTO> byMyTrafficId = trafficTimeService.findByMyTrafficId(id);
        model.addAttribute("myTrafficList",byMyTrafficId);
        return "/myTraffic/myTrafficTime";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id, @RequestParam("trafficId") Long trafficId){
        trafficTimeService.deleteById(id);
        return "redirect:/trafficTime/?id="+trafficId;
    }

    @GetMapping("/update")
    public String update(@ModelAttribute TrafficTimeDTO trafficTimeDTO,@RequestParam("id") Long id){
        trafficTimeService.update(trafficTimeDTO);
        return "redirect:/trafficTime/?id="+id;
    }

    @GetMapping("/check")
    public @ResponseBody String timeCheck(@RequestParam("start") Double start , @RequestParam("end") Double end , @RequestParam("id") Long id ,@RequestParam("timeId") Long timeId){
        String check = trafficTimeService.timeCheck(id,start,end,timeId);
        return check;
    }

    @GetMapping("/timeList")
    public @ResponseBody List<TrafficTimeDTO> timeList(@RequestParam("id") Long id){
        List<TrafficTimeDTO> byTrafficId = trafficTimeService.findByTrafficId(id);
        System.out.println("byTrafficId = " + byTrafficId);
        return byTrafficId;
    }
}
