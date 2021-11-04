package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.ConversationEntity;
import com.example.happyprogramming.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity,Long> {
    //User1: sender, User2: receiver
        ConversationEntity findByUser1AndUser2(UserEntity user1, UserEntity user2);

        ConversationEntity findById(long id);
}