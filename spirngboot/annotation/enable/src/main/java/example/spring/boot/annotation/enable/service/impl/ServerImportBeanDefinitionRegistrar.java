package example.spring.boot.annotation.enable.service.impl;

import example.spring.boot.annotation.enable.annotation.EnableServerExtend;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.stream.Stream;

public class ServerImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        // 读取 EnableServerExtend 中所有的属性方法，本例仅有 type() 属性方法
        // 其中 Key 为属性方法的名称, value 为属性方法的返回对象
        Map<String, Object> annotationAttributes =
                importingClassMetadata.getAnnotationAttributes(EnableServerExtend.class.getName());
        // 复用 {@link ServerImportSelector} 实现
        // 筛选 Class 名称集合
        String[] selectedClassNames = ServerImportSelector.getImportClassNames(annotationAttributes);
        // 创建 bean 定义
        Stream.of(selectedClassNames)
                // 转化为 BeanDefinitionBuilder 对象
                .map(BeanDefinitionBuilder::genericBeanDefinition)
                // 转化为 BeanDefinition 对象
                .map(BeanDefinitionBuilder::getBeanDefinition)
                // 注册 BeanDefinition 到 BeanDefinitionRegistry
                .forEach(beanDefinition ->
                        BeanDefinitionReaderUtils
                                .registerWithGeneratedName(beanDefinition, registry));
    }
}
