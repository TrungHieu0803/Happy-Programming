package com.example.happyprogramming.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CV")
public class CVEntity {
    @Id
    @Column(name = "mentor_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "title",length = 50)
    private String title;

    @Column(length = 500)
    private String profession;

    @Column(name = "social_media_contact",length = 500)
    private String socialMediaContact;

    @Column(length = 2000)
    private String introduction;

    @Column(length = 1000)
    private String achievement;

    @ManyToMany
    @JoinTable(name = "mentor_skills",
            joinColumns = @JoinColumn(name = "mentor_id"), //ở class nào thì thì joinColumn này sẽ là khóa chính của table mang tên class đó
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<SkillEntity> skills ;

}
