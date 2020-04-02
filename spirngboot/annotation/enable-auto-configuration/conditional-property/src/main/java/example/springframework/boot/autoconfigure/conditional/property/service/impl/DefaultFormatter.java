package example.springframework.boot.autoconfigure.conditional.property.service.impl;


import example.springframework.boot.autoconfigure.conditional.property.service.Formatter;

public class DefaultFormatter implements Formatter {

    @Override
    public String format(Object object) {
        return String.valueOf(object);
    }
}