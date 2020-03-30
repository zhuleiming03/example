package example.spring.boot.annotationaliasfor.test;

import example.spring.boot.annotationaliasfor.annotation.DemoOne;
import example.spring.boot.annotationaliasfor.domain.InstanceOne;
import example.spring.boot.annotationaliasfor.domain.InstanceTwo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

@Configuration
@ComponentScan(includeFilters = @ComponentScan.Filter(DemoOne.class),
        useDefaultFilters = false)
@EnableTransactionManagement
public class TestOne {

    public static void main(String[] args) {

        System.out.println("--------- 用反射方式加载注解（@AliasFor无效）-------");

        DemoOne one = InstanceOne.class.getAnnotation(DemoOne.class);
        System.out.printf("InstanceOne 注解 @DemoOne name: %s, param: %s \n", one.name(), one.param());

        DemoOne two = InstanceTwo.class.getAnnotation(DemoOne.class);
        System.out.printf("InstanceTwo 注解 @DemoOne name: %s, param: %s \n", two.name(), two.param());

        System.out.println("--------- 用ASM方式加载注解 -----------------------");

        // 用ASM方式加载注解
        SimpleMetadataReaderFactory metadataReaderFactory =
                new SimpleMetadataReaderFactory();
        try {
            AnnotationMetadata annotationMetadata = metadataReaderFactory
                    .getMetadataReader(InstanceOne.class.getName())
                    .getAnnotationMetadata();

            annotationMetadata.getAnnotationTypes().forEach(annotationType -> {
                Map<String, Object> methods = annotationMetadata.getAnnotationAttributes(annotationType);
                if (!CollectionUtils.isEmpty(methods)) {
                    methods.forEach((k, v) -> System.out.printf("InstanceOne 注解 @%s %s = %s\n",
                            ClassUtils.getShortName(annotationType), k, v));
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            AnnotationMetadata annotationMetadata = metadataReaderFactory
                    .getMetadataReader(InstanceTwo.class.getName())
                    .getAnnotationMetadata();

        } catch (Exception e) {
            System.out.println("@AliasFor 在同一注解中成对出现,赋值不一致则会报错");
        }
    }
}
