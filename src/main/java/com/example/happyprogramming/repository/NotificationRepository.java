package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.NotificationEntity;
import com.example.happyprogramming.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {

    ArrayList<NotificationEntity> findByStatusAndUsers(int status,UserEntity user);

    ArrayList<NotificationEntity> findTop5ByUsersOrderByCreatedDateDesc(UserEntity user);
}
