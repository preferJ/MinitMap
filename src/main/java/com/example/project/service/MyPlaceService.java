package com.example.project.service;

import com.example.project.dto.MyPlaceDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyPlaceEntity;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.MyPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyPlaceService {

    private final MyPlaceRepository myPlaceRepository;

    private final MemberRepository memberRepository;

    public List<MyPlaceDTO> findByEmail(String memberEmail){
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByMemberEmail(memberEmail);
        if(memberEntityOptional.isPresent()){
            MemberEntity memberEntity = memberEntityOptional.get();
            List<MyPlaceEntity> myPlaceEntityList = myPlaceRepository.findByMemberEntity(memberEntity);
            List<MyPlaceDTO> myPlaceList = new ArrayList<>();
            for(MyPlaceEntity entityList : myPlaceEntityList){
                myPlaceList.add(MyPlaceDTO.toMyplaceDTO(entityList));
            }
            return myPlaceList;
        }
        else {
            return null;
        }
    }

    public void save(MyPlaceDTO myPlaceDTO) {
        Optional<MemberEntity> memberOptionalEntity = memberRepository.findById(myPlaceDTO.getMemberId());
        if (memberOptionalEntity.isPresent()) {
            MemberEntity memberEntity = memberOptionalEntity.get();
            MyPlaceEntity myPlaceEntity = MyPlaceEntity.toMyPlaceSaveEntity(myPlaceDTO, memberEntity);
            myPlaceRepository.save(myPlaceEntity);
        }
    }

    // 이현
    public String saveCheck(Double lat, Double lon, Long id) {
        String check = "ok";
        Optional<MemberEntity> byId = memberRepository.findById(id);
        Optional<MyPlaceEntity> myPlaceEntity = myPlaceRepository.findByMemberEntityAndMyPlaceLatAndMyPlaceLon(byId.get(), lat, lon);
        if (myPlaceEntity.isPresent()){
            check="no";
        }
        return check;
    }
}
