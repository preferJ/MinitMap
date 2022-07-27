package com.example.project.repository;

import com.example.project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
    Optional<MemberEntity> findByMemberPhone(String memberPhone);

    Optional<MemberEntity> findByMemberEmailAndMemberPassword(String memberEmail, String memberPassword);

    Optional<MemberEntity> findByMemberPassword(String memberPassword);
}
