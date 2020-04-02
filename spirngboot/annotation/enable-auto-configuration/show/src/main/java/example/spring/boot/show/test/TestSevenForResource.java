package example.spring.boot.show.test;

import example.springframework.boot.autoconfigure.conditional.resource.service.Formatter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @EnableAutoConfiguration 加载 Resource 条件注解的 @Configuration
 */
@EnableAutoConfiguration
public class TestSevenForResource {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new
                SpringApplicationBuilder(TestThree.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // 获取 Formatter, 来自 FormatterAutoConfiguration
        Map<String, Formatter> beans = context.getBeansOfType(Formatter.class);
        if (beans.isEmpty()) {
            System.out.println("Bean 加载失败");
        } else {
            System.out.println("Bean 加载成功");
        }
        // 关闭当前上下文
        context.close();
    }
}
