package com.example.happyprogramming.controller;


import com.example.happyprogramming.Entity.ConversationReplyEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.UserRepository;
import com.example.happyprogramming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConversationController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/chat/{id}/{mess}")
    public void sendMessage(@DestinationVariable long id,@DestinationVariable String mess) {
        ConversationReplyEntity c = new ConversationReplyEntity();
        c.setContext(mess);
        simpMessagingTemplate.convertAndSend("/topic/messages/"+id, c);
    }


    @GetMapping("/contact")
    public String openContact(@RequestParam("id")long id, Model model){
        UserEntity user = userRepository.findById(id);
        model.addAttribute("receiver",user);
        return "client/box-chat";
    }
}
