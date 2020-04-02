package example.springframework.boot.autoconfigure.conditional.bean.servcie.impl;

import example.springframework.boot.autoconfigure.conditional.bean.servcie.Formatter;

public class DefaultFormatter implements Formatter {

    @Override
    public String format(Object object) {
        return "[String]" + String.valueOf(object);
    }
}