package com.example.happyprogramming.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * @author Admin
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "[User]")
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone",nullable = true)
    private int phone;
    @Column(name = "DoB",nullable = true)
    private Date DoB;
    @Column(name = "sex",nullable = true)
    private boolean sex;
    @Column(name = "avatar",nullable = true)
    private String avatar;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    @Column(name = "is_enabled")
    private boolean isEnabled;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"), //ở class nào thì thì joinColumn này sẽ là khóa chính của table mang tên class đó
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles ;


    @OneToMany(mappedBy = "menteeId")
    private Set<RequestEntity> requestOfMentee;

    @OneToMany(mappedBy = "mentorId")
    private Set<RequestEntity> requestForMentor;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private CVEntity mentorId;

    @OneToMany(mappedBy = "users")
    private Set<NotificationEntity> notification;


}