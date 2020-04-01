package example.spring.boot.annotation.enable.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Web 自动装配
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "example.spring.boot.annotation.enable")
public class TestTwo {
}
