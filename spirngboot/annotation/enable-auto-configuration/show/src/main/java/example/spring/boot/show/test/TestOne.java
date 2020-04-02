package example.spring.boot.show.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.Stream;

/**
 * ComponentScan 获取 Bean 能力有限
 */
@ComponentScan(basePackages = "example.spring.boot")
public class TestOne {


    public static void main(String[] args) {

        // 构建 Annotation 配置驱动 Spring 上下文
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        // 不执行 register() refresh() 则无法注册 bean helloWorld
//        context.register(TestOne.class);
//        context.refresh();
        // 输出当前 Spring 应用上下文中所有注册的 Bean 名称
        System.out.println("当前 Spring 应用上下文中所有注册的 Bean 名称：");
        Stream.of(context.getBeanDefinitionNames())
                .map(name -> "\t" + name)
                .forEach(System.out::println);
        // 关闭上下文
        context.close();
    }
}
