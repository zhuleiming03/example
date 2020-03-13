package example.spring.controller;

import example.spring.event.LoginEvent;
import example.spring.event.LogoutEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    ApplicationEventPublisher publisher;

    @RequestMapping(value = "login")
    public String login() {
        System.out.println("user login");
        this.publisher.publishEvent(new LoginEvent("fairy"));
        return "login";
    }

    @RequestMapping(value = "logout")
    public String logout() {
        System.out.println("user logout");
        this.publisher.publishEvent(new LogoutEvent("fairy"));
        return "logout";
    }
}
