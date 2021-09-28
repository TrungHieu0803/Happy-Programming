package com.example.happyprogramming.controller;

import com.example.happyprogramming.Entity.CVEntity;
import com.example.happyprogramming.Entity.RoleEntity;
import com.example.happyprogramming.Entity.SkillEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.CVRepository;
import com.example.happyprogramming.repository.RoleRepository;
import com.example.happyprogramming.repository.SkillRepository;
import com.example.happyprogramming.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CVController {
    @Autowired
    private CVRepository cvRepo;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/createCV")
    public String goToCreateCV(Model model, HttpSession session){
        List<SkillEntity> list  = skillRepository.findAll();
        model.addAttribute("listSkills", list);
        UserEntity user = (UserEntity) session.getAttribute("userInformation");
        return "client/create-CV";
    }

    @PostMapping("/createCV")
    public  String createCV(HttpServletRequest request, HttpSession session) throws Exception{
        String name = request.getParameter("name");
        String prof = request.getParameter("profession");
        String dob = request.getParameter("dob");
        String intro = request.getParameter("description");
        String achiev = request.getParameter("achievement");
        String phone = request.getParameter("phone");
        String contact = request.getParameter("socialContact");
        String [] skills = request.getParameterValues("skills");

        HashSet<SkillEntity> li = new HashSet<>();
        if (skills != null) {
            for (String i : skills) {
                li.add(skillRepository.findBySkillName(i));
            }
        }

        UserEntity user = (UserEntity) session.getAttribute("userInformation");
        user.setFullName(name);
        user.setDoB(dob);
        user.setPhone(phone);
        HashSet<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_MENTEE"));
        roles.add(roleRepository.findByName("ROLE_MENTOR"));
        user.setRoles(roles);
        userRepository.save(user);

        CVEntity cv = new CVEntity();
        cv.setUser(user);
        cv.setProfession(prof);
        cv.setAchievement(achiev);
        cv.setSocialMediaContact(contact);
        cv.setIntroduction(intro);
        cv.setSkills(li);
        cvRepo.save(cv);
        session.setAttribute("cv", cv);
        return "client/index";
    }
}
