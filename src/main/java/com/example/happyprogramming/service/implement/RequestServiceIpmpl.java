package com.example.happyprogramming.service.implement;


import com.example.happyprogramming.Entity.RequestEntity;
import com.example.happyprogramming.repository.RequestRepository;
import com.example.happyprogramming.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestServiceIpmpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Override
    public void createRequest(RequestEntity requestEntity) {
        requestRepository.save(requestEntity);

    }
}
