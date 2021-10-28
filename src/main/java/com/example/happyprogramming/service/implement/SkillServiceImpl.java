package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.Entity.SkillEntity;
import com.example.happyprogramming.repository.SkillRepository;
import com.example.happyprogramming.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;


@Component
public class SkillServiceImpl implements SkillService {


    @Autowired
    SkillRepository skillRepository;

    @Override
    public ArrayList<SkillEntity> getAllSkill() {
        return skillRepository.getAllSkill();
    }

    @Override
    public HashMap<SkillEntity, Integer> mostSeekedSkill(){
        String sql = "SELECT top 3 COUNT(skill_id) as [Count], skill_id\\n\" +\n" +
                "            \"FROM request_skills\\n\" +\n" +
                "            \"GROUP BY skill_id\\n\" +\n" +
                "            \"ORDER BY COUNT(skill_id) DESC";

        HashMap<SkillEntity, Integer> mostSeeked = new HashMap<>();
        return mostSeeked;
    }

}
