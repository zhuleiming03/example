package example.springframework.boot.autoconfigure.conditional.classes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.springframework.boot.autoconfigure.conditional.classes.service.Formatter;

public class JsonFormatter implements Formatter {

    private final ObjectMapper mapper;

    public JsonFormatter() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public String format(Object object) {
        try {
            return "[JSON]" + mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
