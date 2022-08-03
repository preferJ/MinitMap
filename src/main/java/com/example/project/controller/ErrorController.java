package com.example.project.controller;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.ErrorDTO;
import com.example.project.service.BoardService;
import com.example.project.service.ErrorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/error")
public class ErrorController {
    private final ErrorService errorService;
    private final BoardService boardService;

    @PostMapping("/reportSave")
    public String reportSave(@ModelAttribute ErrorDTO errorDTO) {
        System.out.println("errorDTO = " + errorDTO);
        errorService.save(errorDTO);
        return "/index";
    }
    @GetMapping("/findAll")
    public String findAll(Model model){
       List<ErrorDTO> errorDTOList = errorService.findAll();
        System.out.println("errorDTOList = " + errorDTOList);
       model.addAttribute("errorList", errorDTOList);
        return "/AdminPages/boardError";
    }
}
