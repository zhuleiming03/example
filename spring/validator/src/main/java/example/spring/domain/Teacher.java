package example.spring.domain;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Teacher {

    @Min(value = 100, message = "teacher's id begin since 100")
    private Long id;

    private String name;

    @AssertTrue(message = "teacher's valid must true")
    private Boolean valid;

    @DecimalMax(value = "60", message = "teacher's age must littler than 60")
    @DecimalMin(value = "18", message = "teacher's age must bigger than 18")
    private Integer age;

    @Pattern(
            regexp = "^[a-z0-9`!#$%^&*'{}?/+=|_~-]+(\\.[a-z0-9`!#$%^&*'{}?/+=" +
                    "|_~-]+)*@([a-z0-9]([a-z0-9-]*[a-z0-9])?)+(\\.[a-z0-9]" +
                    "([a-z0-9-]*[a-z0-9])?)*$",
            flags = {Pattern.Flag.CASE_INSENSITIVE},
            message = "teacher's email address error"
    )
    private String email;

}
