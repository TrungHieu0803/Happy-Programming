package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.CVEntity;
import com.example.happyprogramming.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CVRepository extends JpaRepository<CVEntity,Long> {
    CVEntity findByUser(UserEntity user);

    ArrayList<CVEntity> findAll();

    Page<CVEntity> findAll(Pageable pageable);
}
