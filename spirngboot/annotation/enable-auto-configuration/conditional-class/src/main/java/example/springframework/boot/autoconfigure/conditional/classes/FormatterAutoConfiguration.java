package example.springframework.boot.autoconfigure.conditional.classes;

import example.springframework.boot.autoconfigure.conditional.classes.service.Formatter;
import example.springframework.boot.autoconfigure.conditional.classes.service.impl.DefaultFormatter;
import example.springframework.boot.autoconfigure.conditional.classes.service.impl.JsonFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatterAutoConfiguration {

    /**
     * 为了不和 simple 项目中的 Formatter 冲突，重新给 bean id 赋值
     * @return
     */
    @Bean("classFormatter")
    @ConditionalOnMissingClass(value = "com.fasterxml.jackson.databind.ObjectMapper")
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }

    @Bean("classFormatter")
    @ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
    public Formatter jsonFormatter() {
        return new JsonFormatter();
    }
}