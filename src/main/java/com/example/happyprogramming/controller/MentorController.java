package com.example.happyprogramming.controller;


import com.example.happyprogramming.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @GetMapping("/list-suggestion-mentor")
    public String getListSuggestionMentor(){
        return "client/index";
    }

    @GetMapping("/mentor-detail")
    public String mentorDetail(@RequestParam("id") int mentorId, Model model){
        model.addAttribute("mentor",mentorService.findMentorById(mentorId));
        return "client/mentor-detail";
    }

}
