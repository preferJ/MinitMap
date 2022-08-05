package com.example.project.service;

import com.example.project.dto.MyPlaceDTO;
import com.example.project.dto.RutinDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.MyPlaceEntity;
import com.example.project.entity.RutinEntity;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.RutinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RutinService {

    private final RutinRepository rutinRepository;
    private final MemberRepository memberRepository;

    public Long save(RutinDTO rutinDTO) {
        Long rutinId;
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(rutinDTO.getMemberId());
        MemberEntity memberEntity = memberRepository.findById(rutinDTO.getMemberId()).get();
        List<RutinEntity> rutinEntityList = rutinRepository.findByMemberEntityOrderByRutinNumberDesc(optionalMemberEntity.get());
        if(optionalMemberEntity.isPresent()){
            if (rutinEntityList.size()>0){
                RutinEntity rutinEntity = RutinEntity.toRutinSaveEntity(rutinDTO, memberEntity, rutinEntityList.get(0).getRutinNumber() + 1l);
                rutinId = rutinRepository.save(rutinEntity).getRutinId();
            }else{
                RutinEntity rutinEntity = RutinEntity.toRutinSaveEntity(rutinDTO, memberEntity, 0l);
                rutinId = rutinRepository.save(rutinEntity).getRutinId();
            }
            return rutinId;
        }else {
            return null;
        }
    }

    public List<RutinDTO> findByMemberId(Long memberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberId);
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            List<RutinEntity> rutinEntityList = rutinRepository.findByMemberEntityOrderByRutinNumberAsc(memberEntity);
            List<RutinDTO> rutinList = new ArrayList<>();
            for(RutinEntity entityList:rutinEntityList){
                rutinList.add(RutinDTO.toRutinDTO(entityList));
            }
            return rutinList;
        }else {
            return null;
        }
    }
    public List<RutinDTO> findByEmail(String memberEmail) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByMemberEmail(memberEmail);
        if (memberEntityOptional.isPresent()) {
            MemberEntity memberEntity = memberEntityOptional.get();
            List<RutinEntity> rutinEntityList = rutinRepository.findByMemberEntityOrderByRutinNumberAsc(memberEntity);
            List<RutinDTO> rutinList = new ArrayList<>();
            for (RutinEntity entityList : rutinEntityList) {
                rutinList.add(RutinDTO.toRutinDTO(entityList));
            }
            return rutinList;
        } else {
            return null;
        }
    }

    public void textUpDown(Long upId, Long downId) {
        // upId의 넘버와 downId의 넘버를 바꾼다.
        RutinEntity upNumber = rutinRepository.findById(upId).get();
        RutinEntity downNumber = rutinRepository.findById(downId).get();
        Long up = upNumber.getRutinNumber();
        Long down = downNumber.getRutinNumber();
        upNumber.setRutinNumber(down);
        downNumber.setRutinNumber(up);
        rutinRepository.save(upNumber);
        rutinRepository.save(downNumber);
    }

    public void deleteById(Long id) {
        rutinRepository.deleteById(id);
    }

    public void updateName(Long id, String name) {
        RutinEntity rutinEntity = rutinRepository.findById(id).get();
        rutinEntity.setRutinName(name);
        rutinRepository.save(rutinEntity);
    }
}
