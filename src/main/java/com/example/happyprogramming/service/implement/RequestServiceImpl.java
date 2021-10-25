package com.example.happyprogramming.service.implement;


import com.example.happyprogramming.Entity.Pagination;
import com.example.happyprogramming.Entity.RequestEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.RequestRepository;
import com.example.happyprogramming.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Override
    public void createRequest(RequestEntity requestEntity, int status) {
        requestEntity.setStatus(status);
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

    @Override
    public Pagination<RequestEntity> findByStatus(int status, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1,10);
        Page<RequestEntity> page = requestRepository.findByStatus(pageRequest,status);
        int totalPages = page.getTotalPages();
        List<RequestEntity> requestList = page.getContent();
        List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
        Pagination<RequestEntity> result = new Pagination<>(requestList,pageNumbers);
        return result;
    }

    @Override
    public void updateRequest(RequestEntity request) {
        Optional<RequestEntity> requestOptional = requestRepository.findById(request.getId());
        RequestEntity requestUpdate = requestOptional.get();
        requestUpdate.setContent(request.getContent());
        requestUpdate.setTitle(request.getTitle());
        requestUpdate.setBudget(request.getBudget());
        requestUpdate.setDeliveryTime(request.getDeliveryTime());
        requestRepository.save(requestUpdate);

    }

    @Override
    public void deleteRequest(Long id) {
        Optional<RequestEntity> requestOptional = requestRepository.findById(id);
        RequestEntity deleteRequest = requestOptional.get();
        deleteRequest.setStatus(4);
        requestRepository.save(deleteRequest);
    }

    @Override
    public void cancelRequest(Long id) {
        Optional<RequestEntity> requestOptional = requestRepository.findById(id);
        RequestEntity cancelRequest = requestOptional.get();
        cancelRequest.setStatus(0);
        cancelRequest.setMentorId(null);
        requestRepository.save(cancelRequest);
    }

}
