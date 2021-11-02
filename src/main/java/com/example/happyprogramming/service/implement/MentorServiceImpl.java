package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.Entity.CVEntity;
import com.example.happyprogramming.Entity.Pagination;
import com.example.happyprogramming.Entity.SkillEntity;
import com.example.happyprogramming.repository.CVRepository;
import com.example.happyprogramming.repository.SkillRepository;
import com.example.happyprogramming.repository.UserRepository;
import com.example.happyprogramming.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class MentorServiceImpl implements MentorService {

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public ArrayList<CVEntity> getAllMentor() {
        return cvRepository.findAll();
    }

    @Override
    public CVEntity findMentorById(long id) {
        return cvRepository.findByUser(userRepository.findById(id));
    }

    @Override
    public Pagination<CVEntity> getPaginatedMentors(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1,10);
        Page<CVEntity> page = cvRepository.findAll(pageRequest);
        int totalPages = page.getTotalPages();
        List<CVEntity> cvList = page.getContent();
        List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
        Pagination<CVEntity> result = new Pagination<>(cvList,pageNumbers);
        return result;
    }

    @Override
    public ArrayList<CVEntity> findMentorBySkill(Long skillId) {
        SkillEntity skill = skillRepository.getSkillEntityById(skillId);
        ArrayList<CVEntity> mentor = cvRepository.findCVEntityBySkills(skill);
        return mentor;
    }

}
