package example.spring.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import example.spring.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(
        basePackages = "example.spring",
        useDefaultFilters = false,
        includeFilters =
        @ComponentScan.Filter(RestController.class)
)
public class RestServletContextConfiguration {
    public RestServletContextConfiguration() {
        System.out.println("Class RestServletContextConfiguration init");
    }

    @Bean
    public ObjectMapper objectMapper() {
        System.out.println("Class ObjectMapper init");
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE,
                false);
        return mapper;
    }

}

