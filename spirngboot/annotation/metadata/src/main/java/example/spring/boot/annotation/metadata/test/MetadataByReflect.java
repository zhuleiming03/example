package example.spring.boot.annotation.metadata.test;

import example.spring.boot.annotation.metadata.annotation.Demo;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Demo(name="This is a reflect test!")
public class MetadataByReflect {

    public static void main(String[] args) {

        // Class 实现了 AnnotatedElement 接口
        AnnotatedElement annotatedElement = MetadataByReflect.class;

        System.out.println("----------- 注解 Demo ---------------------");
        // 从 AnnotatedElement 获取 Demo 注解详情
        Demo demo = annotatedElement.getAnnotation(Demo.class);
        // 打印注解属性
        printAnnotationsAttribute(demo);

        System.out.println("----------- 元注解 ------------------------");
        // 获取 Demo 所有的元注解
        Set<Annotation> metaAnnotationSet = getMetaAnnotations(demo);
        // 打印元注解属性
        metaAnnotationSet.forEach(MetadataByReflect::printAnnotationsAttribute);
    }


    private static void printAnnotationsAttribute(Annotation annotation) {

        Class<?> annotationType = annotation.annotationType();

        // 完全 java 反射实现（ReflectionUtils 为 Spring 反射工具）
        ReflectionUtils.doWithMethods(annotationType,
                // 执行 Method 反射调用
                method -> System.out.printf("@%s.%s() = %s\n",
                        annotationType.getSimpleName(),
                        method.getName(),
                        ReflectionUtils.invokeMethod(method, annotation)),
                // 选择无参数方法
                method -> method.getParameterCount() == 0
        );
    }

    private static Set<Annotation> getMetaAnnotations(Annotation annotation) {

        Annotation[] metaAnnotations = annotation.annotationType().getAnnotations();

        // 没有找到，返回空集合
        if (ObjectUtils.isEmpty(metaAnnotations)) {
            return Collections.emptySet();
        }

        // 获取所有非 Java 标准元注解集合
        // 如 @Target @Document 等
        // 它们因相互依赖，将导致递归不断
        // 通过 java.lang.annotation 包名排除
        Set<Annotation> metaAnnotationsSet = Stream.of(metaAnnotations)
                .filter(metaAnnotation -> !Target.class.getPackage().equals(
                        metaAnnotation.annotationType().getPackage()))
                .collect(Collectors.toSet());

        // 递归查找元注解的元注解集合
        Set<Annotation> metaMetaAnnotationSet = metaAnnotationsSet.stream()
                .map(MetadataByReflect::getMetaAnnotations)
                .collect(HashSet::new, Set::addAll, Set::addAll);

        // 添加递归结果
        metaAnnotationsSet.addAll(metaMetaAnnotationSet);

        return metaAnnotationsSet;
    }

    private static void printDemoAttribute(Demo demo) {
        System.out.println("----------- demo.name() ------------------");
        String nameAttribute = demo.name();
        System.out.println(nameAttribute);
    }
}
