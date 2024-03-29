package com.example.project.service;

import com.example.project.dto.TrafficDTO;
import com.example.project.entity.*;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.LikeCheckRepository;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.TrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrafficService {

    private final TrafficRepository trafficRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final LikeCheckRepository likeCheckRepository;


    public Long save(TrafficDTO trafficDTO, Long memberId) {
        Optional<MemberEntity> entity = memberRepository.findById(memberId);
        Long returnId = trafficRepository.save(TrafficEntity.trafficSaveEntity(trafficDTO, entity.get())).getTrafficId();
        return returnId;
    }


    public List<TrafficDTO> findAll() {
        List<TrafficEntity> trafficEntities = trafficRepository.findAll();
        List<TrafficDTO> trafficDTOList = new ArrayList<>();
        for (TrafficEntity trafficEntity : trafficEntities) {
            trafficDTOList.add(TrafficDTO.toTrafficDTO(trafficEntity));
        }
        return trafficDTOList;
    }


    public List<TrafficDTO> findByMemberId(Long id) {
        List<TrafficEntity> byMemberEntity = trafficRepository.findByMemberEntity(memberRepository.findById(id).get());
        List<TrafficDTO> trafficDTOList = new ArrayList<>();
        for (TrafficEntity trafficEntity : byMemberEntity) {
            trafficDTOList.add(TrafficDTO.toTrafficDTO(trafficEntity));
        }
        return trafficDTOList;
    }

    public Long adminSave(TrafficDTO trafficDTO, Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).get();
        TrafficEntity trafficEntity = trafficRepository.save(TrafficEntity.trafficSaveEntity(trafficDTO, memberEntity));
        trafficEntity.setTrafficName("admin" + trafficEntity.getTrafficId());
        trafficRepository.save(trafficEntity);
        return trafficEntity.getTrafficId();
    }

    public List<TrafficDTO> findByEmail(String email) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(email).get();
        List<TrafficEntity> byMemberEntity = trafficRepository.findByMemberEntity(memberEntity);
        List<TrafficDTO> trafficDTOList = new ArrayList<>();
        for (TrafficEntity trafficEntity : byMemberEntity) {
            trafficDTOList.add(TrafficDTO.toTrafficDTO(trafficEntity));
        }
        return trafficDTOList;
    }

    public List<TrafficDTO> adminLike(String email, Long num) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(email).get();
        List<TrafficEntity> trafficEntities;
        if (num == 1) {
            trafficEntities = trafficRepository.findByMemberEntityOrderByTrafficLikeDesc(memberEntity);
        } else {
            trafficEntities = trafficRepository.findByMemberEntityOrderByTrafficLikeAsc(memberEntity);
        }
        List<TrafficDTO> trafficDTOList = new ArrayList<>();
        for (TrafficEntity trafficEntity : trafficEntities) {
            trafficDTOList.add(TrafficDTO.toTrafficDTO(trafficEntity));
        }
        return trafficDTOList;
    }

    public List<TrafficDTO> adminDislike(String email, Long num) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(email).get();
        List<TrafficEntity> trafficEntities;
        if (num == 1) {
            trafficEntities = trafficRepository.findByMemberEntityOrderByTrafficDislikeDesc(memberEntity);
        } else {
            trafficEntities = trafficRepository.findByMemberEntityOrderByTrafficDislikeAsc(memberEntity);
        }
        List<TrafficDTO> trafficDTOList = new ArrayList<>();
        for (TrafficEntity trafficEntity : trafficEntities) {
            trafficDTOList.add(TrafficDTO.toTrafficDTO(trafficEntity));
        }
        return trafficDTOList;
    }

    public void delete(Long id) {
        trafficRepository.deleteById(id);
    }

    public TrafficDTO findById(Long id) {
        return TrafficDTO.toTrafficDTO(trafficRepository.findById(id).get());
    }

    public String findByIdName(Long trafficId) {
        Optional<TrafficEntity> trafficEntity = trafficRepository.findById(trafficId);
        String name = "";
        if (trafficEntity.isPresent()){
            name = trafficEntity.get().getTrafficName();
        }

        return name;
    }

    public Double findByIdLat(Long trafficId) {
        Optional<TrafficEntity> trafficEntity = trafficRepository.findById(trafficId);
        double lat = 0;
        if (trafficEntity.isPresent()){
            lat = trafficEntity.get().getTrafficLat();
        }

        return lat;
    }

    public double findByIdLon(Long trafficId) {
        Optional<TrafficEntity> trafficEntity = trafficRepository.findById(trafficId);
        double lon = 0;
        if (trafficEntity.isPresent()){
            lon = trafficEntity.get().getTrafficLon();
        }

        return lon;
    }

    public void like(Long id, Long num , Long loginId) {
        TrafficEntity trafficEntity = trafficRepository.findById(id).get();
        MemberEntity memberEntity = memberRepository.findById(loginId).get();
        LikeCheckEntity likeCheckEntity = likeCheckRepository.findByTrafficEntityAndMemberEntity(trafficEntity, memberEntity);
        if (likeCheckEntity == null){
            if (num == 1){
                likeCheckRepository.save(LikeCheckEntity.toLikeCheckTrafficSaveEntity(true,memberEntity,trafficEntity));
                trafficEntity.setTrafficLike(trafficEntity.getTrafficLike()+1l);
            }else{
                likeCheckRepository.save(LikeCheckEntity.toLikeCheckTrafficSaveEntity(false,memberEntity,trafficEntity));
                trafficEntity.setTrafficDislike(trafficEntity.getTrafficDislike()+1l);
            }
        }else if(likeCheckEntity.isLikeCheck()){
            if (num == 2){
                likeCheckEntity.setLikeCheck(false);
                likeCheckRepository.save(likeCheckEntity);
                trafficEntity.setTrafficLike(trafficEntity.getTrafficLike()-1l);
                trafficEntity.setTrafficDislike(trafficEntity.getTrafficDislike()+1l);
            }
        }else{
            if (num == 1){
                likeCheckEntity.setLikeCheck(true);
                likeCheckRepository.save(likeCheckEntity);
                trafficEntity.setTrafficLike(trafficEntity.getTrafficLike()+1l);
                trafficEntity.setTrafficDislike(trafficEntity.getTrafficDislike()-1l);
            }
        }
        trafficRepository.save(trafficEntity);
    }
}
