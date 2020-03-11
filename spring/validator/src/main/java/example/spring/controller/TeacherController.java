package example.spring.controller;

import example.spring.domain.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "teacher")
public class TeacherController {

    @Resource
    LocalValidatorFactoryBean validator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String teacherTest(Map<String, Object> model) {

        Teacher teacherA = new Teacher();
        teacherA.setId(1L);
        teacherA.setName("Tom");
        teacherA.setAge(65);
        teacherA.setEmail("abc");
        teacherA.setValid(true);

        Teacher teacherB = new Teacher();
        teacherB.setId(109L);
        teacherB.setName("Sean");
        teacherB.setAge(17);
        teacherB.setEmail("abc@163.com");
        teacherB.setValid(false);

        System.out.println("-------------------------------------");

        Set<ConstraintViolation<Teacher>> violationsA = validator.validate(teacherA);
        if (violationsA.size() > 0) {
            violationsA.forEach(v -> {
                System.out.println("Message: " + v.getMessage());
            });
        }

        System.out.println("-------------------------------------");

        Set<ConstraintViolation<Teacher>> violationsB = validator.validate(teacherB);
        if (violationsB.size() > 0) {
            violationsB.forEach(v -> {
                System.out.println("Message: " + v.getMessage());
            });
        }
        return "return";
    }
}
