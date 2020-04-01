package example.spring.boot.annotation.enable.instance.config;

import example.spring.boot.annotation.enable.annotation.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionMessageConfiguration {

    @ConditionalOnSystemProperty(name = "language", value = "Chinese")
    @Bean("message")
    public String chineseMessage() {
        return "你好 世界";
    }

    @ConditionalOnSystemProperty(name = "language", value = "English")
    @Bean("message")
    public String englishMessage() {
        return "Hello world";
    }
}
