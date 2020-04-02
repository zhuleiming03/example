package example.springframework.boot.autoconfigure.conditional.expression;

import example.springframework.boot.autoconfigure.conditional.expression.service.Formatter;
import example.springframework.boot.autoconfigure.conditional.expression.service.impl.DefaultFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression(value = "${env.expression:true}")
public class FormatterAutoConfiguration {

    /**
     * 为了不和 simple 项目中的 Formatter 冲突，重新给 bean id 赋值
     *
     * @return
     */
    @Bean("expressionFormatter")
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }
}