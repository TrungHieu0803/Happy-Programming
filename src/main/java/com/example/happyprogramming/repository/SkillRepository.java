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


    ArrayList<SkillEntity> findAll();

}
