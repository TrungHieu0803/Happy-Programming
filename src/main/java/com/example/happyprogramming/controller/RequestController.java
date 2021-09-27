package com.example.happyprogramming.controller;


import com.example.happyprogramming.Entity.RequestEntity;
import com.example.happyprogramming.service.RequestService;
import com.example.happyprogramming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Calendar;


@Controller
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping("/create-request")
    public String createRequestPage(){
        return "/client/create-request";
    }
    @PostMapping("/create-request")
    public String createRequest(HttpServletRequest request) {
        RequestEntity requestEntity = new RequestEntity();
        String title = request.getParameter("title");
        String timeDeadLine = request.getParameter("timeDeadLine");
        String content = request.getParameter("content");
        String skill = request.getParameter("skill");
        requestEntity.setTitle(title);
        requestEntity.setDeliveryTime(timeDeadLine);
        requestEntity.setContent(content);
        // tạm thời chưa lưu skill
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        requestEntity.setCreatedDate((java.sql.Date) date);
        requestService.createRequest(requestEntity);
        return "client/index";
    }
}
