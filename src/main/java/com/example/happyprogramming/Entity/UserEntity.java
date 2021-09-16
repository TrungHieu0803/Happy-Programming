package com.example.happyprogramming.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(name = "status",nullable = true)
    private int status;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"), //ở class nào thì thì joinColumn này sẽ là khóa chính của table mang tên class đó
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles ;

    public UserEntity(String fullName,String password,String email,int status){
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.status = status;
    }

    //    quan hệ n-n sẽ có anotation này, kèm theo là join table anotation để tạo bảng trung gian
//    Tạo bảng trung gian, có 2 khóa chính của 2 bảng user và role join vào làm khóa ngoại


}