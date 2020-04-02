package example.springframework.boot.autoconfigure.conditional.classes.service.impl;

import example.springframework.boot.autoconfigure.conditional.classes.service.Formatter;

public class DefaultFormatter implements Formatter {

    @Override
    public String format(Object object) {
        return "[String]" + String.valueOf(object);
    }
}