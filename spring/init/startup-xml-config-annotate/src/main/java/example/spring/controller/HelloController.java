package example.spring.controller;

import example.spring.service.GreetingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
public class HelloController {

    private GreetingService greetingService;

    @ResponseBody
    @RequestMapping(value = "/index", params = {"name"})
    public String helloName(@RequestParam("name") String name) {
        return this.greetingService.getGreeting(name);
    }

    @Inject
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
