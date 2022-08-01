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



}
