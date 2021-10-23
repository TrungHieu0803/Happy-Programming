package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.CVEntity;
import com.example.happyprogramming.Entity.CommentAndRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface RateCommentRepository extends JpaRepository<CommentAndRateEntity,Long> {

    CommentAndRateEntity findByMentorAndMenteeId(CVEntity mentor, Long menteeId);

    CommentAndRateEntity findById(long id);

}
