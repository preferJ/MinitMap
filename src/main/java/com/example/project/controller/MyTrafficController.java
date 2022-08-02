package com.example.project.controller;

import com.example.project.service.MyTrafficService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/myTraffic")
public class MyTrafficController {
    private final MyTrafficService myTrafficService;

    // 이현
    @GetMapping("/save")
    public @ResponseBody String saveCheck(@RequestParam("id") Long id , HttpSession session){
        Long loginId = (Long) session.getAttribute("loginId");
        String result = myTrafficService.saveCheck(id,loginId);
        return result;
    }

    // 이현
    @PostMapping("/save")
    public @ResponseBody String save(@RequestParam("id") Long id , @RequestParam("title") String title,HttpSession session){
        Long loginId = (Long) session.getAttribute("loginId");
        String result = myTrafficService.save(id,loginId,title);
        return result;
    }

}
