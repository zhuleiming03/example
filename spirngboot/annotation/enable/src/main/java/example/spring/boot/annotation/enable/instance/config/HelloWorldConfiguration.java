package example.spring.boot.annotation.enable.instance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld(){
        return "Hello world";
    }
}
