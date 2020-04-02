package example.spring.boot.show.test;


import example.springframework.boot.autoconfigure.service.Formatter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @EnableAutoConfiguration 加载自定义的 @Configuration
 */
@EnableAutoConfiguration
public class TestThree {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new
                SpringApplicationBuilder(TestThree.class)
                .web(WebApplicationType.NONE)
                .run(args);
        // 待格式化对象
        Map<String, Object> data = new HashMap<>();
        data.put("name", "test");
        // 获取 Formatter, 来自 FormatterAutoConfiguration
        Formatter formatter = context.getBean(Formatter.class);
        System.out.printf("formatter.format(data) : %s \n", formatter.format(data));
        // 关闭当前上下文
        context.close();
    }
}
