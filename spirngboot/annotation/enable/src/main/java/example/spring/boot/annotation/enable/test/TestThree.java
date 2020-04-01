package example.spring.boot.annotation.enable.test;

import example.spring.boot.annotation.enable.instance.InstanceFour;
import example.spring.boot.annotation.enable.service.CalculatingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

public class TestThree {

    /**
     * 配置条件装配
     *
     * @param args
     */
    public static void main(String[] args) {

        // 构建 Annotation 配置驱动 Spring 上下文
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        // 注册当前引导类（@Configuration 标注）到 Spring 上下文
        context.register(InstanceFour.class);
        // 启动上下文
        context.refresh();
        // 获取 CalculatingService Bean 对象
        CalculatingService server = context.getBean(CalculatingService.class);
        // 输出累加结果
        server.sum(1, 2, 3, 4, 5);
        // 关闭上下文
        context.close();
    }

    static {
        // 通过 Java 系统属性设置 Spring Profile
        // 以下语句等效于 ConfigurableEnvironment.setActiveProfiles("Java8")
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "Java8");
        // 以下语句等效于 ConfigurableEnvironment.setDefaultProfiles("Java7")
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "Java7");
    }
}
