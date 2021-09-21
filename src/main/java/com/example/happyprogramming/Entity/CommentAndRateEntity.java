package com.example.happyprogramming.Entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "comment_rate")
public class CommentAndRateEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "request_id")
    private RequestEntity request;

    @Column(name = "comment",length = 2000)
    private String comment;

    @Column
    private int rate;

    @Column(name = "created_date")
    private Date createdDate;
}
