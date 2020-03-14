package example.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("example.spring")
public class Bootstrap {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Bootstrap.class);
    }

}
