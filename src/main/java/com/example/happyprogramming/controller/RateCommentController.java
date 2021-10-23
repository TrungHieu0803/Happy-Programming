package com.example.happyprogramming.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RateCommentController {


    @PostMapping("/save-rate-comment")
    public void saveRateComment(HttpServletRequest request){
        int mentorId = Integer.parseInt(request.getParameter("mentorId"));
        String comment = request.getParameter("comment");
        int starNumber = Integer.parseInt("starNumber");

    }
}
