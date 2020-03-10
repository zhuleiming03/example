package example.spring.controller;

import example.spring.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping(value = "home")
public class HomerController {

    @RequestMapping("/")
    public View home(Map<String, Object> model) {
        System.out.println("[info] visit home controller");
        model.put("redirectUrl", "login");
        // RedirectView true: 表示该Url是相对于上下文，
        // eg: http://localhost:8080/Model_View_Controller_war/
        return new RedirectView("/home/{redirectUrl}", true);
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    public String login(Map<String, Object> model) {
        System.out.println("[info] visit login controller");
        return "访问 /home/ 通过 RedirectView 跳转到 /home/login 成功";
    }

    @RequestMapping(value = "dashboard", method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    public String dashboard(Map<String, Object> model) {
        model.put("text", "This is a view page");
        model.put("date", LocalDateTime.now());
        return "home/dashboard";
    }



}
