package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);

    UserEntity findByVerificationCode(String code);

    Optional<UserEntity> findById(Long id);

    UserEntity findById(long id);

}