package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity,Long> {

}
