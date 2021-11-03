package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.ConversationEntity;
import com.example.happyprogramming.Entity.ConversationReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface ConversationReplyRepository extends JpaRepository<ConversationReplyEntity,Long> {

    ArrayList<ConversationReplyEntity> findByConversationId(ConversationEntity conversation);
}
