package com.example.project.service;

import com.example.project.dto.AdminHistoryDTO;
import com.example.project.entity.AdminHistoryEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.repository.AdminHistoryRepository;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminHistoryService {
    private final AdminHistoryRepository adminHistoryRepository;
    private final MemberRepository memberRepository;

    public void save(AdminHistoryDTO adminHistoryDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(adminHistoryDTO.getMemberId());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            System.out.println("memberEntity = " + memberEntity);
          adminHistoryRepository.save(AdminHistoryEntity.toAdminHistorySaveEntity(adminHistoryDTO, memberEntity));
        }
    }
}