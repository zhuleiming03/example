package example.spring.domain;

import lombok.Data;

import javax.validation.Valid;

@Data
public class Student {

    private Long id;

    @Valid
    private Bag bag;
}
