package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);

    UserEntity findByVerificationCode(String code);


}