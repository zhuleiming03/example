package example.spring;

import example.spring.domain.Manager;
import example.spring.service.impl.Car;
import org.springframework.context.annotation.*;

@ComponentScan("example.spring")
public class Bootstrap {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Bootstrap.class);
    }

    /**
     * 方法二：
     * 初始化 manager 前会根据依赖注释 DependsOn 初始化 car
     * 然后会将 car 通过 Resource 注入到方法 setTraffic 中
     * 再调用方法 print
     * @return Manager
     */
    @Bean(name = "manager", initMethod = "print")
    @DependsOn("car")
    public Manager manager() {
        return new Manager();
    }

    /**
     * Primary 存在多个实例 优先选择该实例
     * @return Car
     */
    @Bean(name = "car")
    @Primary
    public Car car() {
        System.out.println("-------------------- 方法二 ----------------------");
        return new Car();
    }
}
