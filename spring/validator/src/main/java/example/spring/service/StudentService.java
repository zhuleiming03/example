package example.spring.service;

import example.spring.domain.Student;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface StudentService {

    /**
     * 获取一个 student
     *
     * @param id 不小于100
     * @return 不为空
     */
    @NotNull(message = "result not null")
    Student getStudent(@Min(value = 100L, message = "sthdent's id bigger than 100") Long id);
}
