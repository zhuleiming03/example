package example.spring.controller;

import example.spring.annotation.RestController;
import example.spring.domain.Form;
import example.spring.domain.JsonMessage;
import example.spring.domain.XmlMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class IndexRestController {

    public IndexRestController() {
        System.out.println("Class IndexRestController(RestController) init");
    }

    @RequestMapping(value = "json", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonMessage jsonByGet() {
        ServletUriComponentsBuilder builder =
                ServletUriComponentsBuilder.fromCurrentServletMapping();

        JsonMessage message = new JsonMessage();
        message.setId(10L);
        message.setInfo("This is a get json message");
        message.setHref(builder.path("/json").build().toString());

        return message;
    }

    @RequestMapping(value = "json", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonMessage jsonByPost(Form form) {
        ServletUriComponentsBuilder builder =
                ServletUriComponentsBuilder.fromCurrentServletMapping();

        JsonMessage message = new JsonMessage();
        message.setId(form.getUserId());
        message.setInfo("This is a post json message");
        message.setHref(builder.path("/json").build().toString());

        return message;
    }


    @RequestMapping(value = "xml", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public XmlMessage xml() {
        ServletUriComponentsBuilder builder =
                ServletUriComponentsBuilder.fromCurrentServletMapping();

        XmlMessage message = new XmlMessage();
        message.setId(20L);
        message.setInfo("This is a xml message");
        message.setHref(builder.path("/xml").build().toString());

        return message;
    }
}

