package com.example.project.service;

import com.example.project.entity.BoardEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyTrafficEntity;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.MyTrafficRepository;
import com.example.project.repository.TrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyTrafficService {
    private final MyTrafficRepository myTrafficRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    public String saveCheck(Long id, Long loginId) {
        String check = "ok";
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        Optional<MemberEntity> memberEntity = memberRepository.findById(loginId);
        Optional<MyTrafficEntity> byMemberEntityAndTrafficEntity = myTrafficRepository.findByMemberEntityAndTrafficEntity(memberEntity.get(), boardEntity.get().getTrafficEntity());
        if (byMemberEntityAndTrafficEntity.isPresent()){
            check = "no";
        }

        return check;
    }

    public String save(Long id, Long loginId, String name) {
        Long save = myTrafficRepository.save(MyTrafficEntity.toSaveMyTrafficEntity(name, memberRepository.findById(loginId).get(), boardRepository.findById(id).get().getTrafficEntity())).getMyTrafficId();
        if (save>0){
            return "ok";
        }else{
            return "no";
        }
    }
}
