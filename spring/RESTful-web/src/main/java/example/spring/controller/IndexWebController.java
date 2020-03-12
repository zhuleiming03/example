package example.spring.controller;


import example.spring.annotation.WebController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@WebController
public class IndexWebController {

    public IndexWebController() {
        System.out.println("Class IndexWebController(WebController) init");
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    @ResponseBody
    public String list() {
        return "This is a web page";
    }
}
