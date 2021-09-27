package com.example.happyprogramming.repository;

import com.example.happyprogramming.Entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestRepository  extends JpaRepository<RequestEntity,Long> {
}
