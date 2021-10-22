package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.RequestEntity;
import com.example.happyprogramming.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public interface RequestRepository  extends JpaRepository<RequestEntity,Long> {
    List<RequestEntity> findRequestEntitiesByMentorIdAndStatus(UserEntity mentorId, int status);
    Optional<RequestEntity> findById(Long id);
    Page<RequestEntity> findByStatus(Pageable pageable, int status);
}
