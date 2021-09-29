package com.example.happyprogramming.controller;

import com.example.happyprogramming.Entity.CVEntity;
import com.example.happyprogramming.Entity.SkillEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.SkillRepository;
import com.example.happyprogramming.service.ICVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CVController {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ICVService ICVService;



    @GetMapping("/createCV")
    public String goToCreateCV(Model model, HttpSession session){
        List<SkillEntity> list  = skillRepository.findAll();
        model.addAttribute("listSkills", list);
        CVEntity cv = ICVService.findByUser((UserEntity) session.getAttribute("userInformation"));
        if(cv != null){
            session.setAttribute("userInformation",cv.getUser());
        }
        model.addAttribute("cv",cv);
        model.addAttribute("newCV",new CVEntity());

        return "client/create-CV";
    }

    @PostMapping("/createCV")
    public  String createCV(@ModelAttribute("newCV") CVEntity cvEntity) {
        ICVService.saveCV(cvEntity);

        return "client/index";
    }
}
