package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    //    ㅅㅎ 테스트 sout 추가
    // 이현 System.out.println("테스트");
    @GetMapping("/")
    public String gps() {
        return "/index";
    }

    // 이현 테스트전용 get
    @GetMapping("/leehyeon")
    public String leehyeon(){
        return "/LeeHyeon/test";
    }

    // 성수 테스트
    @GetMapping("/hss")
    // 홍성수
    public String hss(){
        return "/hss/hss";
    }

    @GetMapping("/hss/search")
    // TLWT-26
    // 홍성수
    public String search(){
        return "/hss/Search";
    }

    @GetMapping("/header")
    public String header() {
        return "/kmj/header";
    }
    @GetMapping("/1tapTest")
    public String tap1() {
        return "/kmj/1tap";
    }

}
