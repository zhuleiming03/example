package example.spring.boot.annotation.enable.service.impl;

import example.spring.boot.annotation.enable.annotation.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

public class OnSystemPropertyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // 获取 ConditionalOnSystemProperty 所有的属性方法值
        MultiValueMap<String, Object> attributes =
                metadata.getAllAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
        // 获取 ConditionalOnSystemProperty#name() 方法值（单值）
        String propertyName = (String) attributes.getFirst("name");
        // 获取 ConditionalOnSystemProperty#value() 方法值（单值）
        String propertyValue = (String) attributes.getFirst("value");
        // 获取系统属性值
        String systemPropertyValue = System.getProperty(propertyName);
        //
        if (Objects.equals(systemPropertyValue, propertyValue)) {
            System.out.printf("系统属性[名称： %s] 找到匹配值： %s \n"
                    , propertyName, propertyValue);
            return true;
        }
        return false;
    }
}
