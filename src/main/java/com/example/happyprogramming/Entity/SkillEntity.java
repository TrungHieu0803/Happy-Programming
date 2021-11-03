package com.example.happyprogramming.Entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
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

    @Column(name = "img")
    private String img;

    public Long getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }
}
