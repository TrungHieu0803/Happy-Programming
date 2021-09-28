package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.CVEntity;
import com.example.happyprogramming.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CVRepository extends JpaRepository<CVEntity,Long> {
    CVEntity findByUser(UserEntity user);
}
