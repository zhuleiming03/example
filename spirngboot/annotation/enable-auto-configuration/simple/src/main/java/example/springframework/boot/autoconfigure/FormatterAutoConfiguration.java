package example.springframework.boot.autoconfigure;

import example.springframework.boot.autoconfigure.service.Formatter;
import example.springframework.boot.autoconfigure.service.impl.DefaultFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatterAutoConfiguration {

    @Bean
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }
}
