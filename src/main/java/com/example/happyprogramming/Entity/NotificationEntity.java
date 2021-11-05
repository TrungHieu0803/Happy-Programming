package com.example.happyprogramming.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;
import java.sql.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name="from_user")
    private UserEntity fromUser;

    @Column(name = "[content]",length = 2000)
    private String content;

    @Column(name = "created_date")
    private String createdDate;

    @Column
    private int status;

}
