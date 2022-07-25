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
import org.springframework.web.bind.annotation.*;

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
    public String board(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<BoardDTO> boardList = boardService.findAllList(pageable);
        model.addAttribute("boardDTOList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
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

    @GetMapping("/free")
    public String free(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boardList = boardService.free(pageable);
        model.addAttribute("boardDTOList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/BoardPages/free";
    }
//이현
    @GetMapping("/traffic")
    public String traffic(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boardList = boardService.traffic(pageable);
        model.addAttribute("boardDTOList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/BoardPages/traffic";
    }
    //이현
    @GetMapping("/admin")
    public String admin(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boardList = boardService.admin(pageable);
        model.addAttribute("boardDTOList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/BoardPages/admin";
    }

    //이현
    @GetMapping("/location")
    public String local(@PageableDefault(page = 1) Pageable pageable, @RequestParam("type") Long id , @RequestParam("local1") String local1, @RequestParam("local2") String local2,Model model,@RequestParam("search") String search){
        Page<BoardDTO> boardList = boardService.location(pageable,id,local1,local2);

        model.addAttribute("boardDTOList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        System.out.println(boardList.getTotalElements());
        model.addAttribute("type",id);
        model.addAttribute("local1",local1);
        model.addAttribute("local2",local2);
        model.addAttribute("search",search);
        return "/BoardPages/type";
    }

    //이현
    @GetMapping("/search")
    public String search(@PageableDefault(page = 1) Pageable pageable,@RequestParam("search") String search,Model model){
        Page<BoardDTO> boardList = boardService.search(pageable,search);
        model.addAttribute("boardDTOList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("search",search);
        return "/BoardPages/search";
    }
}
