package com.example.project.controller;

import com.example.project.dto.MemberDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    // ㅁㅈ
    @GetMapping("/saveForm")
    public String saveForm() {
        return "MemberPages/signUp";
    }
    @PostMapping("/save")
    // ㅁㅈ
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "/index";
    }

    @GetMapping("/loginForm")
    // ㅁㅈ
    public String loginForm() {
        return "Memberpages/login";
    }

}
