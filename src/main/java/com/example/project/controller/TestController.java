package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    //    ㅅㅎ 테스트 sout 추가
    // 이현 System.out.println("테스트");
    @GetMapping("/")
    public String gps() {
        return "/gps";
    }

    @GetMapping("/leehyeon")
    public String leehyeon(){
        return "/LeeHyeon/test";
    }
}
