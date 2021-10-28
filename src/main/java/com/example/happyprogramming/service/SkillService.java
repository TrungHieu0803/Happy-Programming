package com.example.happyprogramming.service;


import com.example.happyprogramming.Entity.SkillEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public interface SkillService {

    ArrayList<SkillEntity> getAllSkill();
    @Query(value = "SELECT top 3 COUNT(skill_id) as [Count], skill_id\n" +
            "FROM request_skills\n" +
            "GROUP BY skill_id\n" +
            "ORDER BY COUNT(skill_id) DESC")
    HashMap<SkillEntity, Integer> mostSeekedSkill();
}
