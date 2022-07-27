package com.example.project.service;

import com.example.project.dto.ErrorDTO;
import com.example.project.dto.MemberDTO;
import com.example.project.entity.MemberEntity;
import com.example.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // ㅁㅈ
        memberRepository.save(MemberEntity.toMemberSaveEntity(memberDTO));
    }

    public MemberDTO login(MemberDTO memberDTO) {
        // ㅁㅈ
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity loginEntity = optionalMemberEntity.get();
            if (loginEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                return MemberDTO.toMemberDTO(loginEntity);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    public String loginCheck(String memberEmail, String memberPassword) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmailAndMemberPassword(memberEmail, memberPassword);
        if(optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            String result = memberEntity.getMemberEmail();
            return result;
        }else {
            return "no";
        }
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return memberDTO;
        } else {
            return null;
        }
    }

    public String findIdForm(String memberPhone) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberPhone(memberPhone);
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            String result = memberEntity.getMemberEmail();
            return result;
        } else {
            return "no";
        }
    }

    public String findPwForm(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            String result = memberEntity.getMemberPassword();
            return result;
        } else {
            return "no";
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toMemberUpdateEntity(memberDTO));
    }

    public void deleteById(Long loginId) {
        memberRepository.deleteById(loginId);
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(MemberEntity member : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(member));
        }
        return memberDTOList;
    }


//    public void deleteById(Long memberId) {
//        memberRepository.deleteById(memberId);
//    }
}
