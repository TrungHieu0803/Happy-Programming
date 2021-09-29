package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.Entity.CVEntity;
import com.example.happyprogramming.Entity.RoleEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.CVRepository;
import com.example.happyprogramming.repository.RoleRepository;
import com.example.happyprogramming.repository.UserRepository;
import com.example.happyprogramming.service.ICVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class CVServiceImpl implements ICVService {

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CVEntity findByUser(UserEntity user) {
        return cvRepository.findByUser(user);
    }

    @Override
    public void saveCV(CVEntity cv) {
        HashSet<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_MENTOR"));
        UserEntity user = userRepository.findByEmail(cv.getUser().getEmail());
        user.setFullName(cv.getUser().getFullName());
        user.setDoB(cv.getUser().getDoB());
        user.setRoles(roles);
        userRepository.save(user);
        cv.setUser(user);
        CVEntity CVupdate = cvRepository.findByUser(user);
        if(CVupdate==null){
            cvRepository.save(cv);
        }
        else{
            CVupdate.setAchievement(cv.getAchievement());
            CVupdate.setIntroduction(cv.getIntroduction());
            CVupdate.setProfession(cv.getProfession());
            CVupdate.setSkills(cv.getSkills());
            CVupdate.setSocialMediaContact(cv.getSocialMediaContact());
            cvRepository.save(CVupdate);
        }
    }
}
