package example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;

@EnableWebMvc
@ComponentScan(basePackages = "example.spring",
        useDefaultFilters = false,includeFilters = @ComponentScan.Filter(Controller.class))
public class ServletContextConfiguration extends WebMvcConfigurerAdapter {

    @Resource
    SpringValidatorAdapter validator;

    @Override
    public Validator getValidator() {
        return this.validator;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
