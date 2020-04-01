package example.spring.boot.annotation.enable.instance;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "example.spring.boot.annotation.enable.service")
@Configuration
public class InstanceFour {
}
