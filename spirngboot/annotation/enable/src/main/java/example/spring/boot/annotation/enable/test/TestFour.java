package example.spring.boot.annotation.enable.test;

import example.spring.boot.annotation.enable.instance.InstanceFive;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFour {

    /**
     * 自定义配置条件装配
     *
     * @param args
     */
    public static void main(String[] args) {

        // 设置 System Property language = Chinese
        System.setProperty("language", "Chinese");
        // 构建 Annotation 配置驱动 Spring 上下文
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        // 注册当前引导类 到 Spring 上下文
        context.register(InstanceFive.class);
        // 启动上下文
        context.refresh();
        // 获取 ConditionMessageConfiguration message Bean 对象
        String message = context.getBean("message", String.class);
        // 输出 message 内容
        System.out.printf(" message Bean 对象： %s\n", message);
        // 关闭上下文
        context.close();
    }
}
