package com.example.happyprogramming.service;

import com.example.happyprogramming.Entity.CVEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface MentorService {

    ArrayList<CVEntity> getAllMentor();

    CVEntity findMentorById(long id);
}
