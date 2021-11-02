package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.Entity.PopularSkill;
import com.example.happyprogramming.Entity.SkillEntity;
import com.example.happyprogramming.repository.SkillCount;
import com.example.happyprogramming.repository.SkillRepository;
import com.example.happyprogramming.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;


@Component
public class SkillServiceImpl implements SkillService {


    @Autowired
    SkillRepository skillRepository;

    public ArrayList<SkillEntity> getAllSkill() {
        return skillRepository.getAllSkill();
    }

    @Override

    public List<SkillCount> mostSeekedSkills(){
        return skillRepository.mostSeekedSkills();
    }

    @Override
    public SkillEntity getSkillEntityById(Long id) {
        return skillRepository.getSkillEntityById(id);
    }

    @Override
    public HashMap<SkillEntity, Long> mostSeekedSkillEntities(List<SkillCount> list){
        HashMap<SkillEntity, Long> li = new HashMap<>();
        SkillEntity a;
        for (SkillCount item : list) {
            a = skillRepository.getSkillEntityById(item.getSkill_id()) ;
            li.put(a, item.getCount());
        }
        return li;
    }

    public List<PopularSkill> getPopularSkill() {
        ArrayList<SkillEntity> listSkill = skillRepository.findAll();
        List<PopularSkill> listPopularSkill = new ArrayList<>();
        for (SkillEntity s: listSkill) {
            listPopularSkill.add(new PopularSkill(s.getId(),s.getSkillName(),s.getImg(),s.getCvEntitySet().size()));
        }
        Collections.sort(listPopularSkill);
        for (PopularSkill s: listPopularSkill) {
            System.out.println(s.getCount());
        }
        return listPopularSkill;
    }

}
