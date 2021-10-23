package com.example.happyprogramming.service;


import com.example.happyprogramming.Entity.CVEntity;
import org.springframework.stereotype.Service;

@Service
public interface RateCommentService {

    String getRateComment(int mentorId,Long menteeId);

    void saveRateComment(int id, String comment, int starNumber);
}
