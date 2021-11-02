package com.example.happyprogramming.service;

import com.example.happyprogramming.Entity.CVEntity;
import com.example.happyprogramming.Entity.Pagination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface MentorService {

    ArrayList<CVEntity> getAllMentor();

    CVEntity findMentorById(long id);

    Pagination<CVEntity> getPaginatedMentors(int pageNumber);

    ArrayList<CVEntity> findMentorBySkill(Long skillId);

}
