package com.example.happyprogramming.service.implement;


import com.example.happyprogramming.Entity.RequestEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.RequestRepository;
import com.example.happyprogramming.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Override
    public void createRequest(RequestEntity requestEntity) {
        requestRepository.save(requestEntity);

    }

    @Override
    public List<RequestEntity> findRequestEntitiesByMentorIdAndStatus(UserEntity id, int status){
        return requestRepository.findRequestEntitiesByMentorIdAndStatus(id,status);
    }

    @Override
    public Optional<RequestEntity> findById(Long id){
        return requestRepository.findById(id);
    }

}
