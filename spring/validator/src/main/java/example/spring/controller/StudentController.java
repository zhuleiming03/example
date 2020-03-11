package example.spring.controller;

import example.spring.domain.Bag;
import example.spring.domain.Student;
import example.spring.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Controller
@RequestMapping(value = "student")
public class StudentController {

    @Resource
    LocalValidatorFactoryBean validator;

    @Resource
    StudentService studentService;

    @RequestMapping(value = "/")
    public String studentTest() {

        Student student = new Student();
        Bag bag = new Bag();
        student.setBag(bag);

        System.out.println("-------------------------------------");

        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        if (violations.size() > 0) {
            violations.forEach(v -> System.out.println("Message:" + v.getMessage()));
        }

        return "return";
    }

    @RequestMapping(value = "/get")
    public String getStudent() {

        System.out.println("-------------------------------------");

        try {
            Student student = studentService.getStudent(10L);
            System.out.println("Student:" + student);
            System.out.println("StudentServiceImpl not validation");
        } catch (ConstraintViolationException e) {
            System.out.println(e);
        }

        return "return";
    }
}
