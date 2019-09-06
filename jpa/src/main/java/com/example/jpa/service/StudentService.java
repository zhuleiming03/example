package com.example.jpa.service;

import com.example.jpa.po.StudentPO;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentService {

    StudentPO insert(StudentPO po);

    Integer delete(Integer studentID);

    Integer updateBirthdayByCourseID(LocalDateTime birthday, Integer studentID);

    StudentPO selectByCourseID(Integer studentID);

    List<StudentPO> selectAll();
}
