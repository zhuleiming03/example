package example.spring.controller;

import example.spring.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.LocalTime;

@Controller
public class HomeController {

    @RequestMapping(value = "send")
    @ResponseBody
    public String SendMessage() throws InterruptedException {
        System.out.println(String.format("[info][%s]start request", LocalTime.now()));
        messageService.sendMessage();
        Thread.sleep(1_000L);
        System.out.println(String.format("[info][%s]end request", LocalTime.now()));
        return "message sending...";
    }

    @Resource
    MessageService messageService;
}
