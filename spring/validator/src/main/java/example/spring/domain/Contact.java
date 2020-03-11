package example.spring.domain;

import example.spring.validation.Email;
import example.spring.validation.NotBlank;
import lombok.Data;

@Data
public class Contact {

    @NotBlank(message = "name not is blank")
    private String name;

    @Email(message = "e-mail error")
    private String email;
}
