package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.Entity.SkillEntity;
import com.example.happyprogramming.repository.SkillRepository;
import com.example.happyprogramming.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class SkillServiceImpl implements SkillService {


    @Autowired
    SkillRepository skillRepository;

    @Override
    public ArrayList<SkillEntity> getAllSkill() {
        return skillRepository.getAllSkill();
    }
}
