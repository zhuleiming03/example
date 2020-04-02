package example.springframework.boot.autoconfigure.service.impl;


import example.springframework.boot.autoconfigure.service.Formatter;

public class DefaultFormatter implements Formatter {

    @Override
    public String format(Object object) {
        return String.valueOf(object);
    }
}
