package example.springframework.boot.autoconfigure.conditional.resource.service.impl;


import example.springframework.boot.autoconfigure.conditional.resource.service.Formatter;

public class DefaultFormatter implements Formatter {

    @Override
    public String format(Object object) {
        return String.valueOf(object);
    }
}