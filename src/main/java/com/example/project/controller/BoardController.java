package com.example.project.controller;

import com.example.project.common.PagingConst;
import com.example.project.dto.*;
import com.example.project.entity.BoardEntity;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.MemberRepository;
import com.example.project.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final TrafficService trafficService;
    private final BoardRepository boardRepository;
    private final LikeCheckService likeCheckService;
    private final TrafficTimeService trafficTimeService;
    private final MyTrafficService myTrafficService;

    private final ErrorService errorService;
    // 이현 시작
    @GetMapping
    public String board(@PageableDefault(page = 1) Pageable pageable, Model model) {
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
    public String saveForm() {
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
    public String save(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginId");
        boardService.save(boardDTO, memberId);
        return "redirect:/board";
    }
    // 이현
    @GetMapping("/trafficChoice")
    public String trafficChoice(Model model, HttpSession session) {
        Long id = (Long) session.getAttribute("loginId");
        List<MyTrafficDTO> trafficDTOList = myTrafficService.findByMemberId(id);
        model.addAttribute("trafficDTOList", trafficDTOList);
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
    public String local(@PageableDefault(page = 1) Pageable pageable, @RequestParam("type") Long id, @RequestParam("local1") String local1, @RequestParam("local2") String local2, Model model) {
        Page<BoardDTO> boardList = boardService.location(pageable, id, local1, local2);
        model.addAttribute("boardDTOList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        System.out.println(boardList.getTotalElements());
        model.addAttribute("type", id);
        model.addAttribute("local1", local1);
        model.addAttribute("local2", local2);
        return "/BoardPages/type";
    }

    //이현
    @GetMapping("/search")
    public String search(@PageableDefault(page = 1) Pageable pageable, @RequestParam("search") String search, Model model) {
        Page<BoardDTO> boardList = boardService.search(pageable, search);
        model.addAttribute("boardDTOList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("search", search);
        return "/BoardPages/search";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model, HttpSession session) {
        BoardEntity boardEntity = boardRepository.findById(id).get();
        if (boardEntity.getBoardType().equals("신호")) {
            Double dnleh = boardEntity.getTrafficEntity().getTrafficLat();
            Double rudeh = boardEntity.getTrafficEntity().getTrafficLon();
            model.addAttribute("dnleh", dnleh);
            model.addAttribute("rudeh", rudeh);
        }
        BoardDTO boardDTO = boardService.findById(id);
        Long memberId = (Long) session.getAttribute("loginId");
        model.addAttribute("board", boardDTO);
        String check = "빔";
        LikeCheckDTO likeCheckDTO = new LikeCheckDTO();
        if (session.getAttribute("loginId") != null) {
            likeCheckDTO = likeCheckService.findCheck(id, memberId);
            if (likeCheckDTO != null) {
                if (likeCheckDTO.isLikeCheck() == true) {
                    check = "참";
                } else {
                    check = "거짓";
                }
            }
        }
        if (boardDTO.getBoardType().equals("신호")) {
            List<TrafficTimeDTO> byTrafficId = trafficTimeService.findByTrafficId(boardDTO.getTrafficId());
//            String startTime = (trafficTimeDTO.getSetStartTime()/10000) + ":" + ((trafficTimeDTO.getSetStartTime()%10000)/100) + ":" + (trafficTimeDTO.getSetStartTime()%100);
            String startTimeArr[] = new String[byTrafficId.size()];
            String endTimeArr[] = new String[byTrafficId.size()];
            int i = 0;
            for (TrafficTimeDTO trafficTimeDTO : byTrafficId) {

                String startTime = "";
                if (trafficTimeDTO.getTrafficApplyStart() / 10000 < 10) {
                    startTime += "0" + (trafficTimeDTO.getTrafficApplyStart() / 10000) + ":";
                } else {
                    startTime += (trafficTimeDTO.getTrafficApplyStart() / 10000) + ":";
                }

                if (((trafficTimeDTO.getTrafficApplyStart() % 10000) / 100) < 10) {
                    startTime += "0" + ((trafficTimeDTO.getTrafficApplyStart() % 10000) / 100) + ":";
                } else {
                    startTime += ((trafficTimeDTO.getTrafficApplyStart() % 10000) / 100) + ":";
                }

                if ((trafficTimeDTO.getTrafficApplyStart() % 100) < 10) {
                    startTime += "0" + (trafficTimeDTO.getTrafficApplyStart() % 100);
                } else {
                    startTime += (trafficTimeDTO.getTrafficApplyStart() % 100);
                }
                startTimeArr[i]=startTime;
                String endTime = "";
                if (trafficTimeDTO.getTrafficApplyEnd() / 10000 < 10) {
                    endTime += "0" + (trafficTimeDTO.getTrafficApplyEnd() / 10000) + ":";
                } else {
                    endTime += (trafficTimeDTO.getTrafficApplyEnd() / 10000) + ":";
                }

                if (((trafficTimeDTO.getTrafficApplyEnd() % 10000) / 100) < 10) {
                    endTime += "0" + ((trafficTimeDTO.getTrafficApplyEnd() % 10000) / 100) + ":";
                } else {
                    endTime += ((trafficTimeDTO.getTrafficApplyEnd() % 10000) / 100) + ":";
                }

                if ((trafficTimeDTO.getTrafficApplyEnd() % 100) < 10) {
                    endTime += "0" + (trafficTimeDTO.getTrafficApplyEnd() % 100);
                } else {
                    endTime += (trafficTimeDTO.getTrafficApplyEnd() % 100);
                }
                endTimeArr[i]=endTime;
                i++;
            }
            model.addAttribute("trafficStart", startTimeArr);
            model.addAttribute("trafficEnd", endTimeArr);
        }
        System.out.println(check);
        model.addAttribute("check", check);
        return "/BoardPages/detail";
    }

    @GetMapping("/like")
    public String like(@RequestParam("like") Long like, @RequestParam("boardId") Long boardId, HttpSession session) {
        Long id = (Long) session.getAttribute("loginId");
        boardService.likeCheck(like, boardId, id);
        return "redirect:/board/detail?id=" + boardId;
    }

    @GetMapping("/trafficMap")
    public String trafficMap(@RequestParam("boardId") Long id, Model model) {
        BoardEntity boardEntity = boardRepository.findById(id).get();
        Double dnleh = boardEntity.getTrafficEntity().getTrafficLat();
        Double rudeh = boardEntity.getTrafficEntity().getTrafficLon();
        model.addAttribute("dnleh", dnleh);
        model.addAttribute("rudeh", rudeh);
        return "/BoardPages/trafficMap";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("boardId") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        BoardEntity boardEntity = boardRepository.findById(id).get();
        model.addAttribute("board", boardDTO);

        if (boardDTO.getBoardType().equals("신호")){
            Double dnleh = boardEntity.getTrafficEntity().getTrafficLat();
            Double rudeh = boardEntity.getTrafficEntity().getTrafficLon();
            model.addAttribute("dnleh", dnleh);
            model.addAttribute("rudeh", rudeh);
            model.addAttribute("trafficId",boardEntity.getTrafficEntity().getTrafficId());
            return "/BoardPages/trafficUpdate";
        }else{
            return "/BoardPages/update";
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginId");
        boardDTO.setMemberId(memberId);
        boardService.update(boardDTO);
        return "redirect:/board/detail?id=" + boardDTO.getBoardId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("boardId") Long id) {
        boardService.delete(id);
        return "redirect:/board";
    }

    @GetMapping("/3tap")
    public @ResponseBody List<BoardDTO> tap3(@RequestParam("type") String type) {
        List<BoardDTO> boardDTOS = boardService.hots(type);
        // 주간 인기글은 50개만 가져감
        List<BoardDTO> boardDTOList = new ArrayList<>();
        if (boardDTOS.size() > 50) {
            for (int i = 0; i < 50; i++) {
                boardDTOList.add(boardDTOS.get(i));
            }
            System.out.println(boardDTOList);
            return boardDTOList;
        } else {
            return boardDTOS;
        }
    }
    @GetMapping("/findByIdList/{loginId}")
    // ㅁㅈ  내가 쓴 글 보러 가는 메서드
    public String findByIdList(@PathVariable Long loginId, Model model){
        List<BoardDTO> boardDTOList = boardService.findByList(loginId);
        System.out.println("boardDTOList = " + boardDTOList);
        model.addAttribute("boardList", boardDTOList);
        return "/BoardPages/mySave";
    }

    @GetMapping("/report")
    // ㅁㅈ / 신고 탭 이동
    public String report(@RequestParam("id") Long id,Model model) {
        model.addAttribute("boardId",id);
        return "/BoardPages/report";
    }


}
