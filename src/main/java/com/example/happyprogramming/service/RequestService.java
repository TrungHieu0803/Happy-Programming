package com.example.happyprogramming.service;


import com.example.happyprogramming.Entity.RequestEntity;
import com.example.happyprogramming.Entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RequestService {
    void createRequest(RequestEntity requestEntity);
    List<RequestEntity> findRequestEntitiesByMentorIdAndStatus(UserEntity id, int status);
    Optional<RequestEntity> findById(Long id);

}
