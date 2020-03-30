package example.spring.boot.annotation.metadata.test;

import example.spring.boot.annotation.metadata.Service.AsmService;
import example.spring.boot.annotation.metadata.annotation.Demo;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
//import org.springframework.core.type.StandardAnnotationMetadata;
//import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;

@Demo(name = "test")
public class MetadataByAsm {

    public static void main(String[] args) throws IOException {

        // 构造 MetadataReaderFactory 实例
        //MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        SimpleMetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();

        // 读取 ASM 的 MetadataReader 信息
        MetadataReader metadataReader = metadataReaderFactory
                .getMetadataReader(MetadataByAsm.class.getName());

        // 注解的元信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // AnnotationMetadataReadingVisitor ASM 方式读取
        //AnnotationMetadata annotationMetadata = new AnnotationMetadataReadingVisitor(ClassLoader.getSystemClassLoader());
        // StandardAnnotationMetadata java 反射方式读取
        //AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(Asm.class);
        AsmService.printAnnotationMetadata(annotationMetadata);

        // 类的资源信息
        Resource resource = metadataReader.getResource();
        AsmService.printResource(resource);

        // 类的元信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
    }
}
