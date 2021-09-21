package com.example.happyprogramming.Entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "skill")
public class SkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "skill_name")
    private String skillName;

    @ManyToMany(mappedBy = "skills")
    private Set<CVEntity> cvEntitySet;

    @ManyToMany(mappedBy = "skills")
    private Set<RequestEntity>  requestEntities;

}
