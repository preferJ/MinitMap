package com.example.project.repository;

import com.example.project.entity.MyPlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPlaceRepository extends JpaRepository<MyPlaceEntity,Long> {
}
