package example.spring.boot.show.test;


import example.springframework.boot.autoconfigure.conditional.classes.service.Formatter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @EnableAutoConfiguration 加载 Class 条件注解的 @Configuration
 */
@EnableAutoConfiguration
public class TestFourForClass {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new
                SpringApplicationBuilder(TestThree.class)
                .web(WebApplicationType.NONE)
                .run(args);
        // 待格式化对象
        Map<String, Object> data = new HashMap<>();
        data.put("name", "test class");
        // 获取 Formatter, 来自 FormatterAutoConfiguration
        Formatter formatter = context.getBean(Formatter.class);
        // pom 中 dependency 有 jackson-databind 则为 Json, 反之为 String
        System.out.printf("formatter.format(data) : %s \n", formatter.format(data));
        // 关闭当前上下文
        context.close();
    }
}
