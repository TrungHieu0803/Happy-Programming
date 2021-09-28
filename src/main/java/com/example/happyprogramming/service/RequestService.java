package com.example.happyprogramming.service;


import com.example.happyprogramming.Entity.RequestEntity;
import org.springframework.stereotype.Service;

@Service
public interface RequestService {
    void createRequest(RequestEntity requestEntity);

}
