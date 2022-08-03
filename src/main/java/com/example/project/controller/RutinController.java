package com.example.project.controller;

import com.example.project.dto.MyPlaceDTO;
import com.example.project.dto.RutinDTO;
import com.example.project.service.MyPlaceService;
import com.example.project.service.RutinService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/rutin")
public class RutinController {
    private final RutinService rutinService;

    @PostMapping("/save")
    public @ResponseBody String save(@ModelAttribute RutinDTO rutinDTO){
        System.out.println(rutinDTO);
        Long saveId = rutinService.save(rutinDTO);
        if(saveId != null) {
            return "yes";
        }else{
            return "no";
        }
    }

    //이현
    @GetMapping("/list")
    public @ResponseBody List<RutinDTO> rutinList(HttpSession session){
        Long id = (Long) session.getAttribute("loginId");
        List<RutinDTO> rutinDTOList = rutinService.findByMemberId(id);
        return rutinDTOList;
    }

    @GetMapping("/")
    public String rutin(){
        return "/rutin/rutin";
    }

    @GetMapping("/textUpDown")
    public @ResponseBody String textUpDown(@RequestParam("one") Long upId , @RequestParam("two") Long downId){
        rutinService.textUpDown(upId,downId);
        return "ok";
    }

    @GetMapping("/deleteMyPlace")
    public @ResponseBody String deleteMyPlace(@RequestParam("id") Long id){
        rutinService.deleteById(id);
        return "ok";
    }

    @GetMapping("/updateName")
    public @ResponseBody String updateName(@RequestParam("id") Long id,@RequestParam("name") String name){
        rutinService.updateName(id,name);
        return "ok";
    }
}
