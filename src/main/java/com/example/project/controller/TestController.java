package com.example.project.controller;

import com.example.project.dto.*;
import com.example.project.dto.TrafficDTO;
import com.example.project.entity.TestEntity;
import com.example.project.repository.TestRepository;
import com.example.project.service.TrafficService;
import com.example.project.service.TrafficTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final TestRepository testRepository;
    private final TrafficService trafficService;
    private final TrafficTimeService trafficTimeService;

    //    ㅅㅎ 테스트 sout 추가
    // 이현 System.out.println("테스트");
    @GetMapping("/")
    public String gps() {
        return "/index";
    }

    // 이현 테스트전용 get
    @GetMapping("/leehyeon")
    public String leehyeon() {
        return "/LeeHyeon/time";
    }

    // 성수 테스트
    @GetMapping("/hss")
    // 홍성수
    public String hss() {
        return "/hss/hss";
    }

    @GetMapping("/hss/search")
    // TLWT-26
    // 홍성수
    public String search() {
        return "/hss/Search";
    }

    @GetMapping("/hss/myprot")
    //홍성수
    public String myprot() {
        return "/hss/myprot";
    }

    @GetMapping("/kmj/header")
    // ㅁㅈ
    public String header() {
        return "/kmj/header";
    }

    @GetMapping("/1tapTest")
    // ㅁㅈ
    public String tap1(@RequestParam(value = "page_lat", required = false,defaultValue = "0") Double page_lat,
                       @RequestParam(value = "page_lng", required = false,defaultValue = "0") Double page_lng,
                       Model model) {
        model.addAttribute("page_lat",page_lat);
        model.addAttribute("page_lng",page_lng);
        return "/kmj/1tap";
    }

    @GetMapping("/2tapTest")
    // ㅁㅈ
    public String tap2() {
        return "/kmj/2tap";
    }

    @GetMapping("/3tapTest")
    // ㅁㅈ
    public String tap3() {
        return "/kmj/3tap";
    }

    @GetMapping("/4tapTest")
    // ㅁㅈ
    public String tap4() {
        return "/kmj/4tap";
    }

    //이현
    @PostMapping("/test/test")
    public String testtest(@ModelAttribute TestDTO testDTO, @ModelAttribute TrafficDTO trafficDTO) {
        System.out.println("testDTO = " + testDTO + ", trafficDTO = " + trafficDTO);
        testRepository.save(TestEntity.toEntity(testDTO));
        return "/LeeHyeon/time";
    }

    //이현
    @GetMapping("/test/time")
    public String testTime() {
        String sCurTime = null;
        String sMinTime = "112000";
        String sMaxTime = "114000";
        String sTime = "";

        sCurTime = new java.text.SimpleDateFormat("HHmmss", java.util.Locale.KOREA).format(new java.util.Date());

        if (sCurTime.compareTo(sMinTime) >= 0 && sCurTime.compareTo(sMaxTime) < 0) {
            System.out.println("반갑습니다람쥐");
        }
        TimerTask task = new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                String sCurTime = null;
                Long sMinTime = 112000L;
                Long sMaxTime = 134900L;
                Long startTime = 114830L;

                String sTime = "";
                sCurTime = new java.text.SimpleDateFormat("HHmmss", java.util.Locale.KOREA).format(new java.util.Date());

                if (sCurTime.compareTo(String.valueOf(sMinTime)) >= 0 && sCurTime.compareTo(String.valueOf(sMaxTime)) < 0) {
                    System.out.println("반갑습니다람쥐" + i);
                    if (sCurTime.compareTo(String.valueOf(startTime)) == 0) {
                        System.out.println("현재시간입니다람쥐" + i);
                    }
                    i++;
                }

            }
        };
        return "/LeeHyeon/time";
    }


    // ㅅㅎ 테스트 페이지 이동 메서드
    @GetMapping("/jshTest")
    public String jshTest() {
        return "/jsh/Test";
    }

    // ㅅㅎ 테스트 신호등 저장 메서드
    @GetMapping("/jshTestTrafficSave")
    public String jshTestTrafficSave(@ModelAttribute TrafficDTO trafficDTO,
                                     @ModelAttribute TrafficTimeDTO trafficTimeDTO,
                                     HttpSession session) {
        System.out.println("trafficDTO = " + trafficDTO);
        System.out.println("trafficTimeDTO = " + trafficTimeDTO);
        Long id = trafficService.save(trafficDTO, 1L);
        trafficTimeService.save(id, trafficTimeDTO);
        return "/jsh/Test";
    }
// ㅅㅎ 테스트 로그인
    @PostMapping("/jshTestlogin")
    public String jshTestlogin(HttpSession session, @ModelAttribute MemberDTO memberDTO) {
        System.out.println(memberDTO);
        session.setAttribute("memberId", memberDTO.getMemberEmail());
        return "/jsh/Test";
    }
// ㅅㅎ 일단 다 가져오자 메서드
    @GetMapping("/jshTestPrintTraffic")
    public String jshTestPrintTraffic(Model model) {
        List<TrafficDTO> trafficDTOList = trafficService.findAll();
        List<TrafficTimeDTO> trafficTimeDTOList = trafficTimeService.findAll();
        model.addAttribute("trafficDTOList", trafficDTOList);
        model.addAttribute("trafficTimeDTOList", trafficTimeDTOList);
        return "/jsh/Test";
    }


}
