package example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/index")
    public String hello() {
        return "hello world";
    }

    @ResponseBody
    @RequestMapping("/*")
    public String index() {
        return "This url not found: 404!";
    }
}
