package com.example.happyprogramming.controller;


import com.example.happyprogramming.Entity.RequestEntity;
import com.example.happyprogramming.Entity.SkillEntity;
import com.example.happyprogramming.service.MentorService;
import com.example.happyprogramming.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @Autowired
    private SkillService skillService;

    @GetMapping("/mentor-detail")
    public String mentorDetail(@RequestParam("id") long mentorId,@RequestParam(value = "recommend",required = false) boolean recommend, Model model){
        model.addAttribute("recommend", recommend);
        model.addAttribute("mentor",mentorService.findMentorById(mentorId));
        ArrayList<SkillEntity> listSkill = skillService.getAllSkill();
        model.addAttribute("listSkill",listSkill);
        model.addAttribute("requestForm",new RequestEntity());
        model.addAttribute("mentorId",mentorId);
        return "client/mentor-detail";
    }

}
