package com.example.happyprogramming.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Mento")
public class MentorEntity {
    @Id
    @Column(name = "mentor_id", nullable = false)
    private Long id;
    private String img;
    private String title;
    private String description;
    private String time;
    private String skill;
    private String cost;
    private String address;
}
