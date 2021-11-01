package com.example.happyprogramming.service;


import com.example.happyprogramming.Entity.SkillEntity;
import com.example.happyprogramming.repository.SkillCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public interface SkillService {
    SkillEntity getSkillEntityById(Long id);
    ArrayList<SkillEntity> getAllSkill();
    List<SkillCount> mostSeekedSkills();
    HashMap<SkillEntity, Long> mostSeekedSkillEntities(List<SkillCount> list);
}
