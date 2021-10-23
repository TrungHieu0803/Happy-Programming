package com.example.happyprogramming.service;


import com.example.happyprogramming.Entity.CVEntity;
import org.springframework.stereotype.Service;

@Service
public interface RateCommentService {

    public String getRateComment(int mentorId,int menteeId);
}
