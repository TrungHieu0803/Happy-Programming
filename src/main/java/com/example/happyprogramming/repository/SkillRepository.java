package com.example.happyprogramming.repository;


import com.example.happyprogramming.Entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity,Long> {


    @Query(value = "select s from SkillEntity s")
    ArrayList<SkillEntity> getAllSkill();

    SkillEntity getSkillEntityById(Long id);

    @Query(value = "SELECT top 3 COUNT(skill_id) as count, skill_id\n" +
            "FROM request_skills\n" +
            "GROUP BY skill_id\n" +
            "ORDER BY COUNT(skill_id) DESC",nativeQuery = true)
    List<SkillCount> mostSeekedSkills();

    ArrayList<SkillEntity> findAll();

}
