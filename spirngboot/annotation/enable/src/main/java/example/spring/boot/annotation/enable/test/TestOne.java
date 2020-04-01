package example.spring.boot.annotation.enable.test;

import example.spring.boot.annotation.enable.instance.InstanceThree;
import example.spring.boot.annotation.enable.instance.InstanceTwo;
import example.spring.boot.annotation.enable.instance.InstanceOne;
import example.spring.boot.annotation.enable.service.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestOne {

    /**
     * 模块装配
     *
     * @param args
     */
    public static void main(String[] args) {

        // 构建 Annotation 配置驱动 Spring 上下文
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();

        // 基于 “注解驱动” 实现 @Enable 模块
        //one(context);
        // 基于 “接口编程” 实现 @Enable 模块 ImportSelector
        //two(context);
        // 基于 “接口编程” 实现 @Enable 模块 ImportBeanDefinitionRegister
        three(context);

        // 关闭上下文
        context.close();
    }

    /**
     * 基于 “接口编程” 实现 @Enable 模块 ImportBeanDefinitionRegister
     *
     * @param context
     */
    private static void three(AnnotationConfigApplicationContext context) {

        // 注册当前引导类（@Configuration 标注）到 Spring 上下文
        context.register(InstanceThree.class);
        // 启动上下文
        context.refresh();
        // 获取 Server Bean 对象,实际为 FtpServer
        Server server = context.getBean(Server.class);
        // 启动服务器
        server.start();
        // 关闭服务器
        server.stop();
    }

    /**
     * 基于 “接口编程” 实现 @Enable 模块 ImportSelector
     *
     * @param context
     */
    private static void two(AnnotationConfigApplicationContext context) {

        // 注册当前引导类（@Configuration 标注）到 Spring 上下文
        context.register(InstanceTwo.class);
        // 启动上下文
        context.refresh();
        // 获取 Server Bean 对象,实际为 HttpServer
        Server server = context.getBean(Server.class);
        // 启动服务器
        server.start();
        // 关闭服务器
        server.stop();
    }

    /**
     * 基于 “注解驱动” 实现 @Enable 模块
     *
     * @param context
     */
    private static void one(AnnotationConfigApplicationContext context) {

        // 注册引导类（@Configuration 标注）到 Spring 上下文
        context.register(InstanceOne.class);
        // 启动上下文
        context.refresh();
        // 获取名称为 "helloWorld" 的 Bean 对象
        String helloWorld = context.getBean("helloWorld", String.class);
        // 输出用户名称： Hello world"
        System.out.printf("helloWorld = %s \n", helloWorld);
    }
}
