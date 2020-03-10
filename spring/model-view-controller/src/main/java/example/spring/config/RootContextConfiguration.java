package example.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@ComponentScan(basePackages = "example.spring",excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootContextConfiguration {
}
