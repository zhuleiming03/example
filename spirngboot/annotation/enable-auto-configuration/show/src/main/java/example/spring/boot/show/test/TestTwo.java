package example.spring.boot.show.test;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @EnableAutoConfiguration 过滤指定的 bean 不加载
 */
@EnableAutoConfiguration(exclude = {SpringApplicationAdminJmxAutoConfiguration.class,
        CacheAutoConfiguration.class})
public class TestTwo {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TestTwo.class)
                .web(WebApplicationType.NONE)
                .run(args)
                .close();
    }
}
