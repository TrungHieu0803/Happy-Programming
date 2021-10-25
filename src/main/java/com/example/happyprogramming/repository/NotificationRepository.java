package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {

    ArrayList<NotificationEntity> findByStatus(int status);

    ArrayList<NotificationEntity> findTop5ByOrderByCreatedDateDesc();
}
