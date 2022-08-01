package com.example.project.controller;

import com.example.project.dto.MyPlaceDTO;
import com.example.project.dto.RutinDTO;
import com.example.project.service.MyPlaceService;
import com.example.project.service.RutinService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/rutin")
public class RutinController {
    private final RutinService rutinService;

    @PostMapping("/save")
    public @ResponseBody String save(@ModelAttribute RutinDTO rutinDTO){
        System.out.println("들어옴");
        System.out.println(rutinDTO);
        Long saveId = rutinService.save(rutinDTO);
        if(saveId != null) {
            return "yes";
        }else{
            return "no";
        }
    }
}
