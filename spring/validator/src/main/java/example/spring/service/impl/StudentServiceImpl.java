package example.spring.service.impl;

import example.spring.domain.Bag;
import example.spring.domain.Student;
import example.spring.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public Student getStudent(Long id) {
        Bag bag = new Bag();
        bag.setColor("red");
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
