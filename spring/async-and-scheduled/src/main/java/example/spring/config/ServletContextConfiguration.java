package example.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = "example.spring",
        useDefaultFilters = false,includeFilters = @ComponentScan.Filter(Controller.class))
public class ServletContextConfiguration {

}
