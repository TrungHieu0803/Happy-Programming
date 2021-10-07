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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class CVController {

    @Autowired
    private HttpSession session;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ICVService ICVService;



    @GetMapping("/createCV")
    public String goToCreateCV(Model model){
        ArrayList<SkillEntity> list  = skillRepository.getAllSkill();
        model.addAttribute("listSkills", list);
        CVEntity cv = ICVService.findByUser((UserEntity) session.getAttribute("userInformation"));
        model.addAttribute("cv",cv);
        model.addAttribute("newCV",new CVEntity());
        return "client/create-CV";
    }

    @PostMapping("/createCV")
    public  String createCV(@ModelAttribute("newCV") CVEntity cvEntity) {
        ICVService.saveCV(cvEntity);
        if(cvEntity.getSkills()==null)
            System.out.println("Nguyen trung hieu");
        CVEntity cv = ICVService.findByUser((UserEntity) session.getAttribute("userInformation"));
        if(cv != null){
            session.setAttribute("userInformation",cv.getUser());
            session.setAttribute("role","mentorAndMentee");
            session.setAttribute("skills",cv.getSkills());
        }
        return "client/index";
    }

}
