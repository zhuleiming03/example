package com.example.jpa.serviceImpl;

import com.example.jpa.po.StudentPO;
import com.example.jpa.repository.StudentRepository;
import com.example.jpa.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentPO insert(StudentPO po) {
        return repository.save(po);
    }

    public Integer delete(Integer studentID) {
        if (studentID == null) {
            return null;
        } else {
            return repository.removeByStudentID(studentID);
        }
    }

    public Integer updateBirthdayByCourseID(LocalDateTime birthday,Integer studentID) {
        if (studentID == null) {
            return null;
        } else {
            return repository.changeBirthdayByStudentID(studentID, birthday);
        }
    }

    public StudentPO selectByCourseID(Integer studentID) {
        if (studentID == null) {
            return null;
        } else {
            return repository.findByStudentID(studentID);
        }
    }

    public List<StudentPO> selectAll(){
        return repository.findAll();
    }
}
