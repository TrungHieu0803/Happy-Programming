package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.Entity.CVEntity;
import com.example.happyprogramming.repository.CVRepository;
import com.example.happyprogramming.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MentorServiceImpl implements MentorService {

    @Autowired
    private CVRepository cvRepository;

    @Override
    public ArrayList<CVEntity> getAllMentor() {
        return cvRepository.findAll();
    }
}
