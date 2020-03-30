package example.spring.boot.annotation.metadata.test;

import example.spring.boot.annotation.metadata.annotation.Demo;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.AnnotatedElement;

@Demo(name = "attribute",value = "demo")
public class Attribute {

    public static void main(String[] args) {

        AnnotatedElement annotatedElement = Attribute.class;

        // @Controller 被 @RestController 隐性覆盖了
        // 显性覆盖请参考 @AliasFor
        AnnotationAttributes controller = AnnotatedElementUtils
                .getMergedAnnotationAttributes(annotatedElement, Controller.class);
        print(controller);

        AnnotationAttributes restController = AnnotatedElementUtils
                .getMergedAnnotationAttributes(annotatedElement, RestController.class);
        print(restController);

        AnnotationAttributes demo = AnnotatedElementUtils
                .getMergedAnnotationAttributes(annotatedElement, Demo.class);
        print(demo);
    }

    private static void print(AnnotationAttributes annotationAttributes) {

        System.out.printf("注解 @%s 属性集合： \n"
                , annotationAttributes.annotationType().getName());

        annotationAttributes.forEach((name, value) ->
                System.out.printf("\t 属性 %s : %s \n", name, value));
    }
}
