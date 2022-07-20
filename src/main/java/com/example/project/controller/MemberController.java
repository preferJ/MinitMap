package com.example.project.controller;

import com.example.project.dto.MemberDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    // ㅁㅈ
    // ㅅㅎ 주석 위치 수정
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "/index";
    }


    @GetMapping("/loginForm")
    // ㅁㅈ
    public String loginForm() {
        return "MemberPages/login";
    }
    //ㅅㅎ setAttribute에 id --> loginId 로 수정함
    @PostMapping("/login")
    public String login(MemberDTO memberDTO, HttpSession session) {
        // ㅁㅈ
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            session.setAttribute("loginId", loginResult.getMemberId());
            return "/MemberPages/login";
        } else {
            return "MemberPages/login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // ㅁㅈ
        session.invalidate();
        return "redirect:/1tapTest";
    }

}
