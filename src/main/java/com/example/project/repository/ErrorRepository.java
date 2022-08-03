package com.example.project.repository;

import com.example.project.entity.ErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ErrorRepository extends JpaRepository<ErrorEntity,Long> {

}
