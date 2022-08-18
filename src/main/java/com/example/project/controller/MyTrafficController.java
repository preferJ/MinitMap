package com.example.project.controller;

import com.example.project.dto.MyTrafficDTO;
import com.example.project.dto.RutinDTO;
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
    public String saveTraffic(@ModelAttribute MyTrafficDTO myTrafficDTO,
                              @ModelAttribute TrafficTimeDTO trafficTimeDTO,
                              @RequestParam Long memberId, HttpSession session) {
        Long myTrafficId = myTrafficService.save(myTrafficDTO, memberId);
        trafficTimeService.save(myTrafficId, trafficTimeDTO);

        return "redirect:/Main";
    }

    @PostMapping("/test11")
    public String test11() {
        System.out.println("MyTrafficController.test11");
        return "/jsh/formTest2";
    }

    @PostMapping("/inBoundTrafficAll")
    public @ResponseBody List<TrafficIntegratedDTO> inBoundTrafficAll(@RequestParam("center") String center,
                                                                      HttpSession session) {
        System.out.println("MyTrafficController.inBoundTrafficAll");
        System.out.println("center = " + center);
        Long memberId = (Long) session.getAttribute("loginId");
        List<TrafficIntegratedDTO> trafficIntegratedDTOList = myTrafficService.inBoundFindAll(center, memberId);
        return trafficIntegratedDTOList;
    }

    @GetMapping("/")
    public String myTrafficForm() {
        return "/myTraffic/myTraffic";
    }

    @GetMapping("/list")
    public @ResponseBody List<MyTrafficDTO> myTrafficList(HttpSession session) {
        String email = (String) session.getAttribute("loginEmail");
        List<MyTrafficDTO> myTrafficDTOS = myTrafficService.findByEmail(email);
        return myTrafficDTOS;
    }

    @GetMapping("/textUpDown")
    public @ResponseBody String textUpDown(@RequestParam("one") Long upId, @RequestParam("two") Long downId) {
        myTrafficService.textUpDown(upId, downId);
        return "ok";
    }

    @GetMapping("/deleteMyPlace")
    public @ResponseBody String deleteMyPlace(@RequestParam("id") Long id) {
        myTrafficService.deleteById(id);
        return "ok";
    }

    @GetMapping("/updateName")
    public @ResponseBody String updateName(@RequestParam("id") Long id, @RequestParam("name") String name) {
        myTrafficService.updateName(id, name);
        return "ok";
    }

    @GetMapping("/adminMyTraffic")
    public @ResponseBody String adminMyTraffic(@RequestParam("id") Long id, @RequestParam("name") String name,HttpSession session) {
        Long loginId = (Long) session.getAttribute("loginId");
        myTrafficService.adminMyTraffic(id,name,loginId);
        return "ok";
    }

}
