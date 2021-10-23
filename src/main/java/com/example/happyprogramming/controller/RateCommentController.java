package com.example.happyprogramming.controller;


import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.service.RateCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class RateCommentController {

    @Autowired
    private HttpSession session;

    @Autowired
    private RateCommentService rateCommentService;

    @GetMapping("/get-rate-comment")
    public void getRateComment(@RequestParam("mentorId")int mentorId, HttpServletResponse response)throws IOException {
        UserEntity user =(UserEntity) session.getAttribute("userInformation");
        Long menteeId = new Long(user.getId());


            response.getWriter().print(rateCommentService.getRateComment(mentorId,user.getId()));

    }

    @PostMapping("/save-rate-comment")
    public void saveRateComment(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("rateCommentId"));
        String comment = request.getParameter("comment");
        int starNumber = Integer.parseInt(request.getParameter("starNumber"));
        rateCommentService.saveRateComment(id,comment,starNumber);

    }
}
