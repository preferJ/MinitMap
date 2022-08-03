package com.example.project.controller;

import com.example.project.dto.MyPlaceDTO;
import com.example.project.service.MyPlaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/myPlace")
public class MyPlaceController {
    private final MyPlaceService myPlaceService;

    @GetMapping("/save")
    public String save(@RequestParam("myPlaceLat") Double myPlaceLat,
                       @RequestParam("myPlaceLng") Double myPlaceLng,
                       @RequestParam("myPlaceName") String myPlaceName,
                       @RequestParam(value = "icon",required = false,defaultValue = "0") Long icon,
                       HttpSession session){
        Long id = (Long) session.getAttribute("loginId");
        MyPlaceDTO myPlaceDTO = new MyPlaceDTO(id,myPlaceName,myPlaceLat,myPlaceLng,icon);
        myPlaceService.save(myPlaceDTO);
        return "redirect:/1tapTest";
    }

    // 이현
    @GetMapping("/saveCheck")
    public @ResponseBody String saveCheck(@RequestParam("lat") Double lat , @RequestParam("lon") Double lon,HttpSession session){
        Long id = (Long) session.getAttribute("loginId");
        String result = myPlaceService.saveCheck(lat,lon,id);
        return result;
    }

    @GetMapping("/list")
    public @ResponseBody List<MyPlaceDTO> myPlaceDTOList(HttpSession session){
        String email = (String) session.getAttribute("loginEmail");
        List<MyPlaceDTO> myPlaceDTOList = myPlaceService.findByEmail(email);
        return myPlaceDTOList;
    }

    @GetMapping("/")
    public String myPlace(HttpSession session , Model model){
        Long id = (Long) session.getAttribute("loginId");
        List<MyPlaceDTO> myPlaceDTOList = myPlaceService.findByMemberEntity(id);
        model.addAttribute("myPlaceList",myPlaceDTOList);
        return "/MyPlace/myPlace";
    }

    @GetMapping("/textUpDown")
    public @ResponseBody String textUpDown(@RequestParam("one") Long upId , @RequestParam("two") Long downId){
        myPlaceService.textUpDown(upId,downId);
        return "ok";
    }

    @GetMapping("/deleteMyPlace")
    public @ResponseBody String deleteMyPlace(@RequestParam("id") Long id){
        myPlaceService.deleteById(id);
        return "ok";
    }

    @GetMapping("/updateName")
    public @ResponseBody String updateName(@RequestParam("id") Long id,@RequestParam("name") String name){
        myPlaceService.updateName(id,name);
        return "ok";
    }
}
