package example.springframework.boot.autoconfigure.conditional.property;

import example.springframework.boot.autoconfigure.conditional.property.service.Formatter;
import example.springframework.boot.autoconfigure.conditional.property.service.impl.DefaultFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// matchIfMissing = true 当不存在变量 env 默认匹配成功
@ConditionalOnProperty(prefix = "env", name = "enabled", havingValue = "true", matchIfMissing = true)
public class FormatterAutoConfiguration {

    /**
     * 为了不和 simple 项目中的 Formatter 冲突，重新给 bean id 赋值
     *
     * @return
     */
    @Bean("propertyFormatter")
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }
}
