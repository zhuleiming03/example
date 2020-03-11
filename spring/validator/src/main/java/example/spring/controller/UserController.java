package example.spring.controller;

import example.spring.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Resource
    LocalValidatorFactoryBean validator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String userTest(Map<String, Object> model) {
        User user = new User();

        System.out.println("-------------------------------------");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (violations.size() > 0) {
            violations.forEach(v -> {
                System.out.println("Message: " + v.getMessage());
                System.out.println("MessageTemplate: " + v.getMessageTemplate());
                System.out.println(v);
            });
        }
        return "return";
    }
}
