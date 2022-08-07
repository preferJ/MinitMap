package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
public class MapController {



//    ㅅㅎ 메인 페이지 이동 컨트롤러 //
    @RequestMapping("/main")
    public String mainWindow(){
        return "/MapPages/mainWindow";
    }

}
