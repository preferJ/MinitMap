package com.example.project.service;

import com.example.project.dto.RutinDTO;
import com.example.project.entity.MemberEntity;
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
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(rutinDTO.getMemberId());
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            RutinEntity rutinEntity = RutinEntity.toRutinSaveEntity(rutinDTO, memberEntity);
            Long rutinId = rutinRepository.save(rutinEntity).getRutinId();
            return rutinId;
        }else {
            return null;
        }
    }

    public List<RutinDTO> findByMemberId(Long memberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberId);
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            List<RutinEntity> rutinEntityList = rutinRepository.findByMemberEntity(memberEntity);
            List<RutinDTO> rutinList = new ArrayList<>();
            for(RutinEntity entityList:rutinEntityList){
                rutinList.add(RutinDTO.toRutinDTO(entityList));
            }
            return rutinList;
        }else {
            return null;
        }
    }
}
