package com.example.happyprogramming.controller;

import com.example.happyprogramming.Entity.PopularSkill;
import com.example.happyprogramming.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private SkillService skillService;

    @GetMapping("/adminpage")
    public String goToAdmin(Model model){
        List<PopularSkill> li = skillService.getMostSoughtSkills();
        model.addAttribute("sk0", li.get(0));
        model.addAttribute("sk1", li.get(1));
        model.addAttribute("sk2", li.get(2));
        model.addAttribute("otherCount", skillService.totalSought()-li.get(0).getCount()
                                                                        -li.get(1).getCount()
                                                                        -li.get(2).getCount());
        return "admin/index";
    }
    @GetMapping("/skill")
    public String getSkill(){
        return "client/skill";
    }

}
