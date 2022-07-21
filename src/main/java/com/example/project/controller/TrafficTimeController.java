package com.example.project.controller;

import com.example.project.service.TrafficTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TrafficTimeController {
    private final TrafficTimeService trafficTimeService;


}
