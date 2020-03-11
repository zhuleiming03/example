package example.spring.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Bag {

    @NotNull(message = "bag's color is not null")
    private String color;
}
