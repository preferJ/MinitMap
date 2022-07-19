package com.example.project.repository;

import com.example.project.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity,Long> {
}
