package example.springframework.boot.autoconfigure.conditional.web.service.impl;


import example.springframework.boot.autoconfigure.conditional.web.service.Formatter;

public class DefaultFormatter implements Formatter {

    @Override
    public String format(Object object) {
        return String.valueOf(object);
    }
}