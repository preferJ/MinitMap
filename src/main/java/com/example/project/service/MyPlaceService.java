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

    public List<MyPlaceDTO> findByEmail(String memberEmail) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByMemberEmail(memberEmail);
        if (memberEntityOptional.isPresent()) {
            MemberEntity memberEntity = memberEntityOptional.get();
            List<MyPlaceEntity> myPlaceEntityList = myPlaceRepository.findByMemberEntityOrderByMyPlaceNumberAsc(memberEntity);
            List<MyPlaceDTO> myPlaceList = new ArrayList<>();
            for (MyPlaceEntity entityList : myPlaceEntityList) {
                myPlaceList.add(MyPlaceDTO.toMyplaceDTO(entityList));
            }
            return myPlaceList;
        } else {
            return null;
        }
    }

    public void save(MyPlaceDTO myPlaceDTO) {
        Optional<MemberEntity> memberOptionalEntity = memberRepository.findById(myPlaceDTO.getMemberId());
        List<MyPlaceEntity> byMemberEntity = myPlaceRepository.findByMemberEntityOrderByMyPlaceNumberDesc(memberOptionalEntity.get());
        if (memberOptionalEntity.isPresent()) {
            MemberEntity memberEntity = memberOptionalEntity.get();
            if (byMemberEntity.size()>0){
                MyPlaceEntity myPlaceEntity = MyPlaceEntity.toMyPlaceSaveEntity(myPlaceDTO, memberEntity, byMemberEntity.get(0).getMyPlaceNumber()+1l);
                myPlaceRepository.save(myPlaceEntity);
            }else {
                MyPlaceEntity myPlaceEntity = MyPlaceEntity.toMyPlaceSaveEntity(myPlaceDTO, memberEntity, 1l);
                myPlaceRepository.save(myPlaceEntity);
            }
        }
    }

    // 이현
    public String saveCheck(Double lat, Double lon, Long id) {
        String check = "ok";
        Optional<MemberEntity> byId = memberRepository.findById(id);
        Optional<MyPlaceEntity> myPlaceEntity = myPlaceRepository.findByMemberEntityAndMyPlaceLatAndMyPlaceLon(byId.get(), lat, lon);
        if (myPlaceEntity.isPresent()) {
            check = "no";
        }
        return check;
    }


    public List<MyPlaceDTO> findByMemberEntity(Long id) {
        Optional<MemberEntity> member = memberRepository.findById(id);
        List<MyPlaceEntity> byMemberEntity = myPlaceRepository.findByMemberEntity(member.get());
        List<MyPlaceDTO> myPlaceDTOList = new ArrayList<>();
        for (MyPlaceEntity myPlaceEntity : byMemberEntity){
            myPlaceDTOList.add(MyPlaceDTO.toMyplaceDTO(myPlaceEntity));
        }
        return myPlaceDTOList;
    }

    public void textUp(Long upId, Long downId) {
        // upId의 넘버와 downId의 넘버를 바꾼다.
        MyPlaceEntity upNumberEntity = myPlaceRepository.findById(upId).get();
        MyPlaceEntity downNumberEntity = myPlaceRepository.findById(downId).get();
        Long upNumber = upNumberEntity.getMyPlaceNumber();
        Long downNumber = downNumberEntity.getMyPlaceNumber();
        upNumberEntity.setMyPlaceNumber(downNumber);
        downNumberEntity.setMyPlaceNumber(upNumber);
        myPlaceRepository.save(upNumberEntity);
        myPlaceRepository.save(downNumberEntity);
    }
}
