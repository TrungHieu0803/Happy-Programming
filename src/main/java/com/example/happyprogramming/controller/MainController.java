package com.example.happyprogramming.controller;


import com.example.happyprogramming.Entity.RoleEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.RoleRepository;
import com.example.happyprogramming.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@Controller
public class MainController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/home")
    public String home(){
        return "client/index";
    }


    @GetMapping("/login")
    public String sss(){
        return "client/my-account";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(bCryptPasswordEncoder.encode(password));
        String email = request.getParameter("email");
        UserEntity newUser = new UserEntity(username,bCryptPasswordEncoder.encode(password),email,1);
        HashSet<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_MEMBER"));
        newUser.setRoles(roles);
        userRepo.save(newUser);

        return "client/index";
    }
}
