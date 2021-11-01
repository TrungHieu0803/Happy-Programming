package com.example.happyprogramming.service;


import com.example.happyprogramming.Entity.SkillEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public interface SkillService {

    ArrayList<SkillEntity> getAllSkill();

}
