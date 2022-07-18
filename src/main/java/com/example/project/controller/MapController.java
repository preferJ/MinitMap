package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
public class MapController {

    @RequestMapping("/main")
    public String mainWindow(){
        return "/MapPages/mainWindow";
    }
}
