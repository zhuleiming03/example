package example.spring.domain;

import example.spring.service.Traffic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 方法一
 * 初始化 Student 前会根据依赖注释 DependsOn 初始化 bicycle
 * 然后会将 bicycle 通过 Resource 注入到方法 setTraffic 中
 */
@Service
@DependsOn("bicycle")
public class Student {

    private Traffic traffic;

    public Student() {
        System.out.println("class Student init ");
    }

    /**
     * Qualifier 指定注入实例 bicycle
     *
     * @param traffic 注入对象
     */
    @Resource
    @Qualifier("bicycle")
    public void setTraffic(Traffic traffic) {
        System.out.println("class Student method traffic is " + traffic);
        this.traffic = traffic;
        System.out.println(">>>>>Student go home by " + this.traffic.method());
    }
}
