package example.spring.controller;

import example.spring.domain.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

@Controller
public class ContactController {

    @Resource
    LocalValidatorFactoryBean validator;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String userTest(Map<String, Object> model) {
        Contact contact = new Contact();
        contact.setEmail("abc@163.com");
        contact.setName("   ");

        System.out.println("-------------------------------------");

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
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
