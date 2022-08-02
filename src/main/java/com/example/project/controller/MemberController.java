package com.example.project.controller;

import com.example.project.dto.MemberDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        memberService.save(memberDTO);
        return "/MemberPages/login";
    }
    @PostMapping("/saveCheck")
    public @ResponseBody String saveCheck(@RequestParam String memberEmail) {
      String result = memberService.saveCheck(memberEmail);
        return result;
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
            session.setAttribute("loginNickName", loginResult.getMemberNickname());
            return "/MemberPages/login";
        } else {
            return "MemberPages/login";
        }
    }

    @PostMapping("/loginCheck")
    // ㅁㅈ
    public @ResponseBody String loginCheck(@RequestParam String memberEmail, @RequestParam String memberPassword) {
        String result = memberService.loginCheck(memberEmail, memberPassword);
        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // ㅁㅈ
        session.invalidate();
        return "redirect:/1tapTest";
    }

    @GetMapping("/admin")
    // ㅁㅈ
    public String admin() {
        return "/AdminPages/adminHistory";
    }

    @GetMapping("/myPage/{loginId}")
    // ㅁㅈ
    public String myPage(@PathVariable Long loginId, Model model) {
        MemberDTO memberDTO = memberService.findById(loginId);
        model.addAttribute("member", memberDTO);
        return "/MemberPages/myPage";
    }

    @PostMapping("/findIdForm")
    // ㅁㅈ 아이디 찾기
    public @ResponseBody String findIdForm(@RequestParam String memberPhone) {
        String result = memberService.findIdForm(memberPhone);
        return result;
    }

    @PostMapping("/findPwForm")
    // 비밀번호 찾기 ㅁㅈ
    public @ResponseBody String findPwForm(@RequestParam String memberEmail) {
        String result = memberService.findPwForm(memberEmail);
        return result;
    }

    @PostMapping("/pwCheck")
    // ㅁㅈ
    public @ResponseBody String passwordCheck(@RequestParam String memberPassword) {
        String result = memberService.findByMemberPassword(memberPassword);
        return result;
    }

    @GetMapping("/deleteById/{loginId}")
    // ㅁㅈ
    public String deleteById(@PathVariable Long loginId, HttpSession session) {
        System.out.println("loginId = " + loginId);
        memberService.deleteById(loginId);
        session.invalidate();
        return "redirect:/1tapTest";
    }

    @GetMapping("/findAll")
    // ㅁㅈ
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "AdminPages/adminHistory";
    }
//    @GetMapping("/updateForm/{loginId}")
//    public String updateForm(@PathVariable Long loginId, Model model) {
//        System.out.println("loginId = " + loginId);
//       MemberDTO memberDTO = memberService.findById(loginId);
//       model.addAttribute("member", memberDTO);
//       return "/MemberPages/update";
//    }
    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/member/myPage/" + memberDTO.getMemberId();
    }

    @GetMapping("/deleteId/{id}")
    public String deleteId(@PathVariable Long id) {
        memberService.deleteId(id);
        return "redirect:/member/findAll";
    }
}
