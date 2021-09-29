package com.example.happyprogramming.service;


import com.example.happyprogramming.Entity.CVEntity;
import com.example.happyprogramming.Entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface ICVService {
    CVEntity findByUser(UserEntity user);

    void saveCV(CVEntity cv);
}
