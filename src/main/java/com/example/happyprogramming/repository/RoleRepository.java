package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.RoleEntity;
import com.example.happyprogramming.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByName(String name);
}