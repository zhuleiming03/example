package example.spring.boot.annotation.metadata.Service;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import sun.reflect.annotation.AnnotationType;

import java.io.IOException;
import java.util.*;

public class AsmService {

    public static void printResource(Resource resource) throws IOException {

        System.out.println("------------- Resource ----------------------");

        if (resource.isReadable()) {
            System.out.println("CLass >>> " + resource.getFilename());
            System.out.println("Path >>> " + resource.getURI().toString());
            System.out.println("Description >>> " + resource.getDescription());
        }
    }

    public static void printAnnotationMetadata(AnnotationMetadata annotationMetadata) {

        System.out.println("------------- AnnotationMetadata ------------");

        // 获取类名 example.spring.boot.annotation.metadata.test.Asm
        //System.out.println(annotationMetadata.getClassName());

        System.out.println("------------- 该类标注的注解如下 -------------");

        // 获取所有注解类型
        Set<String> annotationTypeSet = annotationMetadata.getAnnotationTypes();
        annotationTypeSet.forEach(System.out::println);

        annotationTypeSet.forEach(annotationType -> {
            Map<String, Object> methods = annotationMetadata.getAnnotationAttributes(annotationType);
            if (!CollectionUtils.isEmpty(methods)) {
                methods.forEach((k, v) -> System.out.printf("注解 @%s %s = %s\n",
                        ClassUtils.getShortName(annotationType), k, v));
            }
        });

        System.out.println("------------- 该类标注的元注解如下 ----------");

        // 获取所有的元注解类型
        Set<String> metaAnnotationTypeSet = annotationMetadata.getAnnotationTypes()
                .stream()
                .map(annotationType -> annotationMetadata.getMetaAnnotationTypes(annotationType))
                .collect(LinkedHashSet::new, Set::addAll, Set::addAll);
        metaAnnotationTypeSet.forEach(System.out::println);

        metaAnnotationTypeSet.forEach(annotationType -> {
            Map<String, Object> methods = annotationMetadata.getAnnotationAttributes(annotationType);
            if (!CollectionUtils.isEmpty(methods)) {
                methods.forEach((k, v) -> System.out.printf("元注解 @%s %s = %s\n",
                        ClassUtils.getShortName(annotationType), k, v));
            }
        });
    }
}
