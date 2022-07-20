package com.example.project.controller;

import com.example.project.dto.MemberDTO;
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
    @PostMapping("/save")
    // ㅁㅈ
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "/";
    }


    @GetMapping("/loginForm")
    // ㅁㅈ
    public String loginForm() {
        return "Memberpages/login";
    }
    @PostMapping("/login")
    public String login(MemberDTO memberDTO, HttpSession session) {
        // ㅁㅈ
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            session.setAttribute("id", loginResult.getMemberId());
            return "/kmj/1tap";
        } else {
            return "memberPages/login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // ㅁㅈ
        session.invalidate();
        return "redirect:/kmj/1tap";
    }

}
