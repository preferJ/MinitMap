package com.example.project.controller;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.TrafficDTO;
import com.example.project.service.BoardService;
import com.example.project.service.TrafficService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final TrafficService trafficService;
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

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO){
        System.out.println("boardDTO = " + boardDTO);
        return "redirect:/board";
    }

    @GetMapping("/trafficChoice")
    public String trafficChoice(Model model,HttpSession session){
        Long id = (Long) session.getAttribute("loginId");
        List<TrafficDTO> trafficDTOList = trafficService.findByMemberId(id);
        System.out.println(trafficDTOList);
        model.addAttribute("trafficDTOList",trafficDTOList);
        return "/BoardPages/test";
    }
}
