package com.example.jpa.serviceImpl;

import com.example.jpa.po.CoursePO;
import com.example.jpa.repository.CourseRepository;
import com.example.jpa.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    public CoursePO findByCourseID(Integer id) {
        if (id == null) {
            return null;
        } else {
            return repository.findByCourseID(id);
        }
    }

    public List<CoursePO> findAll() {
        return repository.findAll();
    }

    public CoursePO save(CoursePO po) {
        return repository.save(po);
    }

    public void delete(CoursePO po) {
        repository.delete(po);
    }
}
