package example.spring.controller;

import example.spring.domain.Message;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value = "message")
public class MessageController {

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String inputMessage(Map<String, Object> model) {
        model.put("Message", new Message());
        return "show";
    }

    @RequestMapping(value = "show", method = RequestMethod.POST)
    public ModelAndView checkMessage(Map<String, Object> model, @Valid Message message, Errors errors) {

        System.out.println("-------------------------------------");
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(v -> {
                System.out.println("Field:" + v.getField());
                System.out.println("Code:" + v.getCode());
                System.out.println("Message:" + v.getDefaultMessage());
                System.out.println("Rejected value:" + v.getRejectedValue());
            });
            return new ModelAndView(new RedirectView("/", true, false));
        }

        System.out.println(message);
        return new ModelAndView(new RedirectView("/", true, false));
    }
}
