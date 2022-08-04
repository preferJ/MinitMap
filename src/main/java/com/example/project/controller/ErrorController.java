package com.example.project.controller;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.ErrorDTO;
import com.example.project.service.BoardService;
import com.example.project.service.ErrorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/error")
public class ErrorController {
    private final ErrorService errorService;
    private final BoardService boardService;

    @PostMapping("/reportSave")
    // ㅁㅈ
    public String reportSave(@ModelAttribute ErrorDTO errorDTO) {
        System.out.println("errorDTO = " + errorDTO);
        errorService.save(errorDTO);
        return "/hss/tapDown";
    }
    @GetMapping("/findAll")
    // ㅁㅈ
    public String findAll(Model model){
        System.out.println("123123");
        List<BoardDTO> distinctByBoardEntity = errorService.findDistinctByBoardEntity();
       model.addAttribute("boardList", distinctByBoardEntity);
        return "/AdminPages/boardError";
    }

    @GetMapping("/detail/{id}")
    // ㅁㅈ
    public String detail(@PathVariable Long id, Model model){
       List<ErrorDTO> errorDTOList = errorService.findAllByBoardId(id);
       model.addAttribute("errorList", errorDTOList);
        return "/AdminPages/errorDetail";
    }
}
