package com.example.happyprogramming.controller;


import com.example.happyprogramming.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @GetMapping("/list-suggestion-mentor")
    public String getListSuggestionMentor(){
        return "client/index";
    }


}
