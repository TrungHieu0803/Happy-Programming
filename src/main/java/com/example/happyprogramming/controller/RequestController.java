package com.example.happyprogramming.controller;


import com.example.happyprogramming.Entity.RequestEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.service.RequestService;
import com.example.happyprogramming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Calendar;


@Controller
public class RequestController {

    @Autowired
    HttpSession session;

    @Autowired
    RequestService requestService;

    @GetMapping("/create-request")
    public String createRequestPage(Model model){
        model.addAttribute("requestForm",new RequestEntity());
        return "/client/create-request";
    }

    @PostMapping("/create-request")
    public String createRequest(HttpServletRequest request) {
        RequestEntity requestEntity = new RequestEntity();
        String title = request.getParameter("title");
        String deliveredTime = request.getParameter("deliveredTime");
        String content = request.getParameter("content");
        String skill = request.getParameter("skill");
        String budget = request.getParameter("budget");
        UserEntity user =(UserEntity) session.getAttribute("userInformation");
        requestEntity.setMenteeId(user);
        requestEntity.setTitle(title);
        requestEntity.setDeliveryTime(deliveredTime);
        requestEntity.setBudget(budget);
        requestEntity.setContent(content);
        // tạm thời chưa lưu skill
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        requestEntity.setCreatedDate(date);
        requestService.createRequest(requestEntity);
        return "client/index";
    }
}
