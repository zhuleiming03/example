package example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add() {
        return "This is a get request";
    }

    @ResponseBody
    @RequestMapping(value = "query", params = {"id", "level=3"})
    public String query(@RequestParam("id") String id) {
        return "This is a level 3 member, ID:" + id;
    }

    @ResponseBody
    @RequestMapping(value = "header", headers = {"X-client"})
    public String header() {
        return "test";
    }

    @ResponseBody
    @RequestMapping(value = "consumes", consumes = {"text/json", "application/json"},
            produces = {"text/json", "application/json"})
    public String consumes() {
        return "test";
    }
}
