package example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;


@ComponentScan(basePackages = "example.spring",
        excludeFilters = @ComponentScan.Filter(Controller.class))
@Configuration
public class RootContextConfiguration {

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor =
                new MethodValidationPostProcessor();
        processor.setValidator(this.localValidatorFactoryBean());
        return processor;
    }
}
