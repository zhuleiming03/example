package example.spring.boot.annotation.enable.service.impl;

import example.spring.boot.annotation.enable.test.TestTwo;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebMvcServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return of(TestTwo.class);
    }

    @Override
    protected String[] getServletMappings() {
        return of("/*");
    }

    private static <T> T[] of(T... valuse) {
        return valuse;
    }
}
