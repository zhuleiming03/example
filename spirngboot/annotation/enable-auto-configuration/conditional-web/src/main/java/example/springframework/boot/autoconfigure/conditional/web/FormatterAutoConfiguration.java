package example.springframework.boot.autoconfigure.conditional.web;

import example.springframework.boot.autoconfigure.conditional.web.service.Formatter;
import example.springframework.boot.autoconfigure.conditional.web.service.impl.DefaultFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
public class FormatterAutoConfiguration {

    /**
     * 为了不和 simple 项目中的 Formatter 冲突，重新给 bean id 赋值
     *
     * @return
     */
    @Bean("webFormatter")
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }
}