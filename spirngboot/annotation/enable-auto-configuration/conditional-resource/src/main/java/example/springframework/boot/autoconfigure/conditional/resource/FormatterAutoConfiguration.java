package example.springframework.boot.autoconfigure.conditional.resource;

import example.springframework.boot.autoconfigure.conditional.resource.service.impl.DefaultFormatter;
import example.springframework.boot.autoconfigure.conditional.resource.service.Formatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 只有当 META-INF 文件夹下存在 application.factories 时 该配置才能生效
@ConditionalOnResource(resources = "META-INF/application.factories")
public class FormatterAutoConfiguration {

    /**
     * 为了不和 simple 项目中的 Formatter 冲突，重新给 bean id 赋值
     *
     * @return
     */
    @Bean("resourceFormatter")
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }
}
