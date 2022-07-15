package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/")
    public String gps(){
        return "/gps";
    }

    @GetMapping("/hss")
    public String hss(){
        return "/hss/hss";
    }
}
