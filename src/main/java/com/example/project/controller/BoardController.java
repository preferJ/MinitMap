package com.example.project.controller;

import com.example.project.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    // 이현 시작
    @GetMapping
    public String board(){
        return "/BoardPages/index";
    }
    @GetMapping("/save")
    public String saveForm(){
        return "/BoardPages/save";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/board";
    }

}
