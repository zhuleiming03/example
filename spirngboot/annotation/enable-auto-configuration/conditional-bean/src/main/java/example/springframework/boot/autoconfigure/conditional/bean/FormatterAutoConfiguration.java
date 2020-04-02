package example.springframework.boot.autoconfigure.conditional.bean;


import com.fasterxml.jackson.databind.ObjectMapper;
import example.springframework.boot.autoconfigure.conditional.bean.servcie.Formatter;
import example.springframework.boot.autoconfigure.conditional.bean.servcie.impl.DefaultFormatter;
import example.springframework.boot.autoconfigure.conditional.bean.servcie.impl.JsonFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatterAutoConfiguration {

    /**
     * 为了不和 simple 项目中的 Formatter 冲突，重新给 bean id 赋值
     *
     * @return
     */
    @Bean("beanFormatter-defaultFormatter")
    @ConditionalOnMissingClass(value = "com.fasterxml.jackson.databind.ObjectMapper")
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }

    @Bean("beanFormatter-jsonFormatter")
    @ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
    @ConditionalOnMissingBean(type = "com.fasterxml.jackson.databind.ObjectMapper")
    public Formatter jsonFormatter() {
        return new JsonFormatter();
    }

    @Bean("beanFormatter-objectMapperFormatter")
    @ConditionalOnBean(ObjectMapper.class)
    public Formatter objectMapperFormatter(ObjectMapper objectMapper) {
        return new JsonFormatter(objectMapper);
    }
}