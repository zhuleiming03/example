package example.spring.domain;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Message {

    @Min(value = 100L, message = "message's id must bigger than 100")
    private Long id;

    @NotNull(message = "message's info is not null")
    private String info;
}
