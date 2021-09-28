package com.example.happyprogramming.repository;


import com.example.happyprogramming.Entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity,Long> {

    @Query(value = "select s from SkillEntity s")
    ArrayList<SkillEntity> getAllSkill();
}
