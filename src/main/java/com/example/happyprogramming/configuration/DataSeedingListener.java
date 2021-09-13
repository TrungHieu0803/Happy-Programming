package com.example.happyprogramming.configuration;


import com.example.happyprogramming.Entity.RoleEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.RoleRepository;
import com.example.happyprogramming.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new RoleEntity("ROLE_ADMIN"));
        }

        if (roleRepository.findByName("ROLE_MEMBER") == null) {
            roleRepository.save(new RoleEntity("ROLE_MEMBER"));
        }

        // Admin account
        if (userRepository.findByUsername("admin@gmail.com") == null) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("123456"));

            HashSet<RoleEntity> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            roles.add(roleRepository.findByName("ROLE_MEMBER"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        // Member account
        if (userRepository.findByUsername("member@gmail.com") == null) {
            UserEntity user = new UserEntity();
            user.setUsername("member@gmail.com");
            user.setPassword(passwordEncoder.encode("123456"));
            HashSet<RoleEntity> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_MEMBER"));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

}
