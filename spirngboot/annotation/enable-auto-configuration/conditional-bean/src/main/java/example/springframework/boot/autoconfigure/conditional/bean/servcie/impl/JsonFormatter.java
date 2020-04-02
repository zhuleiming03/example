package example.springframework.boot.autoconfigure.conditional.bean.servcie.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.springframework.boot.autoconfigure.conditional.bean.servcie.Formatter;

public class JsonFormatter implements Formatter {

    private final ObjectMapper mapper;

    public JsonFormatter() {
        this(new ObjectMapper());
    }

    public JsonFormatter(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
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
