package com.example.jpa.service;

import com.example.jpa.po.CoursePO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {

    CoursePO findByCourseID(Integer id);

    List<CoursePO> findAll();

    CoursePO save(CoursePO po);

    void delete(CoursePO po);
}
