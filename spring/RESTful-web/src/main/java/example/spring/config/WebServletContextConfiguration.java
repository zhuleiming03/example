package example.spring.config;

import example.spring.annotation.WebController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(
        basePackages = "example.spring",
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(WebController.class)
)
public class WebServletContextConfiguration {
    public WebServletContextConfiguration() {
        System.out.println("Class WebServletContextConfiguration init");
    }
}

