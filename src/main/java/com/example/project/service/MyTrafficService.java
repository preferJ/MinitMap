package com.example.project.service;

import com.example.project.dto.MyTrafficDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyTrafficEntity;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.MyTrafficRepository;
import com.example.project.repository.TrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyTrafficService {
    private final MyTrafficRepository myTrafficRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public List<MyTrafficDTO> findByMemberId(Long id) {
        Optional<MemberEntity> byId = memberRepository.findById(id);
        List<MyTrafficEntity> byMemberEntity = myTrafficRepository.findByMemberEntity(byId.get());
        List<MyTrafficDTO> myTrafficDTOS = new ArrayList<>();
        for (MyTrafficEntity myTrafficEntity : byMemberEntity){
            myTrafficDTOS.add(MyTrafficDTO.toSaveMyTrafficDTO(myTrafficEntity));
        }
        return myTrafficDTOS;
    }
}
