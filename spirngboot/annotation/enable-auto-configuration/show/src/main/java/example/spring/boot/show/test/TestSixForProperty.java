package example.spring.boot.show.test;

import example.springframework.boot.autoconfigure.conditional.property.service.Formatter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @EnableAutoConfiguration 加载 Property 条件注解的 @Configuration
 */
@EnableAutoConfiguration
public class TestSixForProperty {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new
                SpringApplicationBuilder(TestThree.class)
                .web(WebApplicationType.NONE)
                // matchIfMissing 默认缺省 加载成功
                // 内部化配置 .properties() 加载失败
                // 外部化配置 application.properties 加载成功，覆盖内部化配置
                .properties("env.enabled=false")
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
