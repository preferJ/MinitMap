package com.example.project.controller;

import com.example.project.common.PagingConst;
import com.example.project.dto.BoardDTO;
import com.example.project.dto.TrafficDTO;
import com.example.project.repository.MemberRepository;
import com.example.project.service.BoardService;
import com.example.project.service.MemberService;
import com.example.project.service.TrafficService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final TrafficService trafficService;
    // 이현 시작
    @GetMapping
    public String board(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardDTOList",boardDTOList);
        return "/BoardPages/index";
    }
    // 이현
    @GetMapping("/save")
    public String saveForm(){
        return "/BoardPages/save";
    }
    // 이현
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/board";
    }
    // 이현
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO,HttpSession session){
        Long memberId = (Long) session.getAttribute("loginId");
        boardService.save(boardDTO ,memberId);
        return "redirect:/board";
    }
    // 이현
    @GetMapping("/trafficChoice")
    public String trafficChoice(Model model,HttpSession session){
        Long id = (Long) session.getAttribute("loginId");
        List<TrafficDTO> trafficDTOList = trafficService.findByMemberId(id);
        model.addAttribute("trafficDTOList",trafficDTOList);
        return "/BoardPages/modal";
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        // ㅁㅈ
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "/kmj/3tap";
    }

//    // 이현 자유게시판 출력
//    @GetMapping("/free")
//    public String free(Model model){
//        List<BoardDTO> boardDTOList = boardService.findFree();
//        model.addAttribute("boardDTOList",boardDTOList);
//        return "/BoardPages/free";
//    }

    @GetMapping("/free")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boardList = boardService.paging(pageable);
        model.addAttribute("boardDTOList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/BoardPages/free";
    }
}
