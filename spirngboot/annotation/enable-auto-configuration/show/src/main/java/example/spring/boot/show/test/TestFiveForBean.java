package example.spring.boot.show.test;

import example.springframework.boot.autoconfigure.conditional.bean.servcie.Formatter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @EnableAutoConfiguration 加载 Bean 条件注解的 @Configuration
 */
@EnableAutoConfiguration
public class TestFiveForBean {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new
                SpringApplicationBuilder(TestThree.class)
                .web(WebApplicationType.NONE)
                .run(args);
        // 待格式化对象
        Map<String, Object> data = new HashMap<>();
        data.put("name", "test bean");
        // 获取 Formatter, 来自 FormatterAutoConfiguration
        Map<String, Formatter> beans = context.getBeansOfType(Formatter.class);
        if (beans.isEmpty()) {
            System.out.println("Bean 不存在");
        } else {
            beans.forEach((beanName, formatter) -> {
                System.out.printf("[Bean name: %s] %s.format(data) : %s \n",
                        beanName, formatter.getClass().getSimpleName(), formatter.format(data));
            });
        }
        // 关闭当前上下文
        context.close();
    }
}
