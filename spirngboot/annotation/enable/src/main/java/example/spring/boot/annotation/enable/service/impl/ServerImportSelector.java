package example.spring.boot.annotation.enable.service.impl;

import example.spring.boot.annotation.enable.annotation.EnableServer;
import example.spring.boot.annotation.enable.service.Server;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class ServerImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 读取 EnableServer 中所有的属性方法，本例仅有 type() 属性方法
        // 其中 Key 为属性方法的名称, value 为属性方法的返回对象
        Map<String, Object> annotationAttributes =
                importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
        return getImportClassNames(annotationAttributes);
    }

    public static String[] getImportClassNames(Map<String, Object> annotationAttributes) {
        // 获取名为 "type" 的属性方法, 并且强制转化成 Server.Type 类型
        Server.Type type = (Server.Type) annotationAttributes.get("type");
        // 导入的类名称数组
        String[] importClassNames = new String[0];
        switch (type) {
            case FTP:
                importClassNames = new String[]{FtpServer.class.getName()};
                break;
            case HTTP:
                importClassNames = new String[]{HttpServer.class.getName()};
                break;
            default:
                break;
        }
        return importClassNames;
    }
}
