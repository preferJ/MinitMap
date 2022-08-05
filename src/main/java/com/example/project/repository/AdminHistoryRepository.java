package com.example.project.repository;

import com.example.project.entity.AdminHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminHistoryRepository extends JpaRepository<AdminHistoryEntity,Long> {



}
