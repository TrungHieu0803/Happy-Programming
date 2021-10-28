package com.example.happyprogramming.repository;


import com.example.happyprogramming.Entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity,Long> {

    @Query(value = "select s from SkillEntity s")
    ArrayList<SkillEntity> getAllSkill();

    @Query(value = "SELECT top 3 COUNT(skill_id)\n" +
            "FROM request_skills\n" +
            "GROUP BY skill_id\n" +
            "ORDER BY COUNT(skill_id) DESC")
    HashMap<SkillEntity, Integer> mostSeekedSkill();


}
