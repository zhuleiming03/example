package com.example.verify.exception;

import lombok.Getter;

@Getter
public class MappingException extends RuntimeException {

    private String property;

    public MappingException(String property) {
        this.property = property;
    }

    public MappingException(String property, String message) {
        super(message);
        this.property = property;

    }

    public MappingException(String property, Exception e) {
        super(e);
        this.property = property;
    }

}
