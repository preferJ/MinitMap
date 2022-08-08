package com.example.project.controller;

import com.example.project.dto.AdminHistoryDTO;
import com.example.project.dto.BoardDTO;
import com.example.project.dto.ErrorDTO;
import com.example.project.service.AdminHistoryService;
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
    private final AdminHistoryService adminHistoryService;

    @PostMapping("/reportSave")
    // ㅁㅈ / 신고 저장
    public String reportSave(@ModelAttribute ErrorDTO errorDTO) {
        errorService.save(errorDTO);
        return "/hss/tapDown";
    }
    @GetMapping("/findAll")
    // ㅁㅈ // 신고받은 게시물 중복 값 없이 출력
    public String findAll(Model model){
        List<BoardDTO> distinctByBoardEntity = errorService.findDistinctByBoardEntity();
       model.addAttribute("boardList", distinctByBoardEntity);
        System.out.println("distinctByBoardEntity = " + distinctByBoardEntity);
        return "/AdminPages/boardError";
    }

    @GetMapping("/detail/{id}")
    // ㅁㅈ // 신고게시물 신고내용
    public String detail(@PathVariable Long id, Model model){
       List<ErrorDTO> errorDTOList = errorService.findAllByBoardId(id);
       model.addAttribute("errorList", errorDTOList);
        return "/AdminPages/errorDetail";
    }
    @GetMapping("/boardCheck/{id}")
    //ㅁㅈ 글 확인 탭 이동 메서드
    public String errorCheck(@PathVariable Long id, Model model){
       BoardDTO boardDTO = boardService.findByBoardId(id);
       model.addAttribute("board", boardDTO);
       return "/AdminPages/boardCheck";
    }
    @PostMapping("/historySave")
    // 신고 처리를 저장 할 때 확인 된거는 확인이라고 띄우기 위한 1값을 넣을 때 업데이트 메서드
    public String historySave(@ModelAttribute AdminHistoryDTO adminHistoryDTO,@RequestParam Long boardId){
        adminHistoryService.save(adminHistoryDTO);
       BoardDTO boardDTO = boardService.findByBoardId(boardId);
       boardDTO.setBoardHits(1L);
       boardService.updateByBoardHits(boardDTO, adminHistoryDTO.getMemberId());
        return "/hss/tapDown";
    }
}
