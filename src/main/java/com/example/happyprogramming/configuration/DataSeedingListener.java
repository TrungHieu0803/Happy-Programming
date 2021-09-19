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

        if (roleRepository.findByName("ROLE_MENTEE") == null) {
            roleRepository.save(new RoleEntity("ROLE_MENTEE"));
        }
        if (roleRepository.findByName("ROLE_MENTOR") == null) {
            roleRepository.save(new RoleEntity("ROLE_MENTOR"));
        }

        // Admin account
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            UserEntity admin = new UserEntity();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("123456"));

            HashSet<RoleEntity> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            roles.add(roleRepository.findByName("ROLE_MENTEE"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        // Member account
        if (userRepository.findByEmail("xuhao9xx@gmail.com") == null) {
            UserEntity user = new UserEntity();
            user.setEmail("member@gmail.com");
            user.setPassword(passwordEncoder.encode("12"));
            user.setEnabled(true);
            HashSet<RoleEntity> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_MENTEE"));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

}
