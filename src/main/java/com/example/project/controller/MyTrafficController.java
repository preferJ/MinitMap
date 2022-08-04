package com.example.project.controller;

import com.example.project.dto.MyTrafficDTO;
import com.example.project.dto.TrafficIntegratedDTO;
import com.example.project.dto.TrafficTimeDTO;
import com.example.project.service.MyTrafficService;
import com.example.project.service.TrafficTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/myTraffic")
public class MyTrafficController {
    private final MyTrafficService myTrafficService;
    private final TrafficTimeService trafficTimeService;

    //     이현
    @GetMapping("/save")
    public @ResponseBody String saveCheck(@RequestParam("id") Long id, HttpSession session) {
        Long loginId = (Long) session.getAttribute("loginId");
        String result = myTrafficService.saveCheck(id, loginId);
        return result;
    }

    //     이현
    @PostMapping("/save")
    public @ResponseBody String save(@RequestParam("id") Long id, @RequestParam("title") String title, HttpSession session) {
        Long loginId = (Long) session.getAttribute("loginId");
        String result = myTrafficService.save(id, loginId, title);
        return result;
    }


    @PostMapping("/saveTraffic")
    public String saveTraffic(@ModelAttribute MyTrafficDTO myTrafficDTO, @ModelAttribute TrafficTimeDTO trafficTimeDTO,@RequestParam Long memberId, HttpSession session) {
        Long myTrafficId =  myTrafficService.save(myTrafficDTO,memberId);
        trafficTimeService.save(myTrafficId,trafficTimeDTO);

        return "redirect:/4tapTest";
    }

    @PostMapping("/test11")
    public String test11(){
        System.out.println("MyTrafficController.test11");
        return "/jsh/formTest2";
    }

    @PostMapping ("/inBoundTrafficAll")
    @ResponseBody
    public List<TrafficIntegratedDTO> inBoundTrafficAll(@RequestParam("center") String center,HttpSession session){
        Long memberId = (Long) session.getAttribute("memberId");
        List<TrafficIntegratedDTO> trafficIntegratedDTOList = myTrafficService.inBoundFindAll(center,memberId);
        return trafficIntegratedDTOList;
    }

}
