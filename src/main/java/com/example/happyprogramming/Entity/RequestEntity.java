package com.example.happyprogramming.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "[request]")
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private UserEntity menteeId;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private UserEntity mentorId;

    @Column(length = 1000)
    private String title;

    @Column
    private double budget;

    @Column(name = "delivery_time")
    private String deliveryTime;

    @Column(length = 3000)
    private String content;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "response_mess")
    private String responseMess;

    @Column
    private int status;

    @ManyToMany
    @JoinTable(name = "request_skills",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<SkillEntity> skills ;

    @OneToOne(mappedBy = "request")
    @PrimaryKeyJoinColumn
    private CommentAndRateEntity commentAndRate;

}
