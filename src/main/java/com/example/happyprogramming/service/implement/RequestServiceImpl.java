package com.example.happyprogramming.service.implement;


import com.example.happyprogramming.Entity.*;
import com.example.happyprogramming.repository.RequestRepository;
import com.example.happyprogramming.service.MentorService;
import com.example.happyprogramming.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    private MentorService mentorService;

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
    public Pagination<RequestEntity> findByStatus(UserEntity mentee,int status, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1,10);
        Page<RequestEntity> page = requestRepository.findByStatusAndAndMenteeId(pageRequest,status,mentee);
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
    public void cancelRequest(Long id) {
        Optional<RequestEntity> requestOptional = requestRepository.findById(id);
        RequestEntity cancelRequest = requestOptional.get();
        cancelRequest.setStatus(0);
        cancelRequest.setMentorId(null);
        requestRepository.save(cancelRequest);
    }

    @Override
    public Pagination<CVEntity> createRequestWithPagination(RequestEntity requestEntity, int status, int pageNumber) {
        requestEntity.setStatus(status);
        requestRepository.save(requestEntity);
        Long skillId = getSkillIdFromRequest(requestEntity);
        return mentorService.findMentorBySkill(skillId,pageNumber);
    }

    @Override
    public Long getSkillIdFromRequest(RequestEntity request) {
        SkillEntity skill = new SkillEntity();
        for (SkillEntity s: request.getSkills()) {
            skill =s;
            break;
        }
        return skill.getId();
    }

    @Override
    public List<TotalRequestMonthly> totalRequestMonthly() {

        int currentYear = LocalDateTime.now().getYear();
        int currentMonth = LocalDateTime.now().getMonthValue();
        int[] listCount = {0,0,0,0,0,0,0,0,0,0,0,0};


        List<RequestEntity> li = requestRepository.findByYear(currentYear);
        try{
            for (RequestEntity req:li) {

                int month =  Integer.parseInt(req.getCreatedDate().substring(3,5));
                listCount[month-1]++;
            }
        }catch(Exception e){
            System.out.println(e);
        }

        List<TotalRequestMonthly> list = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            TotalRequestMonthly a = new TotalRequestMonthly(Month.of(i+1), listCount[i]);
            list.add(a);
            System.out.println("month" + (i+1) + ": "+listCount[i]);
        }
        return list;
    }
}
