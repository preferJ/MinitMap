package com.example.project.service;

import com.example.project.dto.AdminHistoryDTO;
import com.example.project.entity.AdminHistoryEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.repository.AdminHistoryRepository;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
          adminHistoryRepository.save(AdminHistoryEntity.toAdminHistorySaveEntity(adminHistoryDTO, memberEntity));
        }
    }

    public void trafficSave(Long loginId, Long id) {
        MemberEntity memberEntity = memberRepository.findById(loginId).get();
        AdminHistoryDTO adminHistoryDTO = new AdminHistoryDTO();
        adminHistoryDTO.setHistoryMessage(id + "번 신호등 등록");
        adminHistoryDTO.setHistoryType("신호등록");
        adminHistoryRepository.save(AdminHistoryEntity.toAdminHistorySaveEntity(adminHistoryDTO,memberEntity));
    }

    public void trafficUpdate(Long loginId, Long id) {
        MemberEntity memberEntity = memberRepository.findById(loginId).get();
        AdminHistoryDTO adminHistoryDTO = new AdminHistoryDTO();
        adminHistoryDTO.setHistoryMessage(id + "번 신호등 수정");
        adminHistoryDTO.setHistoryType("신호수정");
        adminHistoryRepository.save(AdminHistoryEntity.toAdminHistorySaveEntity(adminHistoryDTO,memberEntity));
    }

    public List<AdminHistoryDTO> findAll() {
        List<AdminHistoryEntity> adminHistoryEntityList= adminHistoryRepository.findAll();
        List<AdminHistoryDTO> adminHistoryDTOList = new ArrayList<>();
        for (AdminHistoryEntity admin: adminHistoryEntityList){
            adminHistoryDTOList.add(AdminHistoryDTO.toAdminHistoryDTO(admin));
        }
        return adminHistoryDTOList;
    }
}