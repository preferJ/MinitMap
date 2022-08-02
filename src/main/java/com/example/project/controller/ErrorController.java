package com.example.project.controller;

import com.example.project.dto.ErrorDTO;
import com.example.project.service.ErrorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/error")
public class ErrorController {
    private final ErrorService errorService;

    @PostMapping("/reportSave")
    public String reportSave(@ModelAttribute ErrorDTO errorDTO) {
        errorService.save(errorDTO);
        return "/BoardPages/index";
    }
}
