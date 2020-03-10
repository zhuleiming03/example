package example.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:setting.properties")
public class SettingConfig {

    public SettingConfig() {
        System.out.println("class SettingConfig init");
    }

    @Value("${name}")
    public String name;

    @Value("${age}")
    public String age;

    @Value("${interest}")
    public String interest;
}
