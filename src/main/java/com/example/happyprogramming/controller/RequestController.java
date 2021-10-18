package com.example.happyprogramming.controller;


import com.example.happyprogramming.Entity.RequestEntity;
import com.example.happyprogramming.Entity.SkillEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.RequestRepository;
import com.example.happyprogramming.service.RequestService;
import com.example.happyprogramming.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


@Controller
public class RequestController {

    @Autowired
    private SkillService skillService;

    @Autowired
    HttpSession session;

    @Autowired
    RequestService requestService;

    @Autowired
    RequestRepository requestRepository;

    @GetMapping("/create-request")
    public String createRequestPage(Model model){
        ArrayList<SkillEntity> listSkill = skillService.getAllSkill();
        model.addAttribute("listSkill",listSkill);
        model.addAttribute("requestForm",new RequestEntity());
        return "/client/create-request";
    }

    @PostMapping("/create-request")
    public String createRequest(@ModelAttribute("requestForm") RequestEntity requestEntity) {
        UserEntity user =(UserEntity) session.getAttribute("userInformation");
        requestEntity.setMenteeId(user);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        requestEntity.setCreatedDate(date);
        requestService.createRequest(requestEntity);
        return "client/index";
    }

    @GetMapping("/invited-request-wait")
    public String listWaitingRequestPage(Model model){
        UserEntity user =(UserEntity) session.getAttribute("userInformation");
        List<RequestEntity> list = requestService.findRequestEntitiesByMentorIdAndStatus(user, 1);
        model.addAttribute("listWaitingRequest",list);
        return "/client/waiting-requests";
    }
    @GetMapping("/invited-request-rejected")
    public String listRejectedRequestPage(Model model){
        UserEntity user =(UserEntity) session.getAttribute("userInformation");
        List<RequestEntity> list = requestService.findRequestEntitiesByMentorIdAndStatus(user, 2);
        model.addAttribute("listRejectedRequest",list);
        return "/client/rejected-requests";
    }
    @GetMapping("/invited-request-approved")
    public String listApprovedRequestPage(Model model){
        UserEntity user =(UserEntity) session.getAttribute("userInformation");
        List<RequestEntity> list = requestService.findRequestEntitiesByMentorIdAndStatus(user, 3);
        model.addAttribute("listApprovedRequest",list);
        return "/client/approved-requests";
    }
    @PostMapping("/reject")
    public String rejectRequest(HttpServletRequest request){
       Long id = Long.parseLong(request.getParameter("id"));
       String response = request.getParameter("response");
        Optional<RequestEntity> re = requestService.findById(id);
        if (re.isPresent()){
            RequestEntity req = re.get();
            req.setStatus(2);
            req.setResponseMess(response);
            requestRepository.save(req);
        }
        return "redirect:/invited-request-wait";
    }
    @PostMapping("/approve")
    public String approveRequest(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("id"));
        String response = request.getParameter("response");
        Optional<RequestEntity> re = requestService.findById(id);
        if (re.isPresent()){
            RequestEntity req = re.get();
            req.setResponseMess(response);
            req.setStatus(3);
            requestRepository.save(req);
        }
        return "redirect:/invited-request-wait";
    }
}
