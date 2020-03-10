package example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/school")
public class SchoolController {

    @ResponseBody
    @RequestMapping("/*")
    public String other() {
        return "hello anyone";
    }

    @ResponseBody
    @RequestMapping("/student/t*")
    public String student() {
        return "hello student";
    }

    @ResponseBody
    @RequestMapping("/student/tom")
    public String tom() {
        return "hello Tom";
    }
}
