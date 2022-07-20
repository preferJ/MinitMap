package com.example.project.repository;

import com.example.project.entity.AdminHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminHistoryRepository extends JpaRepository<AdminHistoryEntity,Long> {
}
