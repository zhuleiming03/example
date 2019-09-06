package com.example.jpa.repository;

import com.example.jpa.po.CoursePO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CoursePO,Integer> {

    CoursePO findByCourseID(Integer id);

    List<CoursePO> findAll();

}
