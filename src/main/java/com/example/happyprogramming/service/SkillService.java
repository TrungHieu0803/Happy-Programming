package com.example.happyprogramming.service;


import com.example.happyprogramming.Entity.SkillEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface SkillService {

    ArrayList<SkillEntity> getAllSkill();
}
