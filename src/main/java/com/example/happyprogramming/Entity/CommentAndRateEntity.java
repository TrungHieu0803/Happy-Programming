package com.example.happyprogramming.Entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "comment_rate")
public class CommentAndRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private CVEntity mentor;

    @Column(name = "comment",length = 2000)
    private String comment;

    @Column
    private int rate;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "mentee_id")
    private Long menteeId;
}
