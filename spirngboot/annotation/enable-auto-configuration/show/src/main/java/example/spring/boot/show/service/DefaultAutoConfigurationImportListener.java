package example.spring.boot.show.service;

import org.springframework.boot.autoconfigure.AutoConfigurationImportEvent;
import org.springframework.boot.autoconfigure.AutoConfigurationImportListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;
import java.util.Set;

public class DefaultAutoConfigurationImportListener implements AutoConfigurationImportListener {

    @Override
    public void onAutoConfigurationImportEvent(AutoConfigurationImportEvent event) {
        // 获取当前 ClassLoader
        ClassLoader classLoader = event.getClass().getClassLoader();

        // 候选的自动装配 Class 名单
        List<String> candidates = SpringFactoriesLoader
                .loadFactoryNames(EnableAutoConfiguration.class, classLoader);
        System.out.println("候选的自动装配 Class 名单：");
        candidates.forEach(System.out::println);

        // 实际的自动装配 Class 名单
        List<String> configurations = event.getCandidateConfigurations();
        System.out.println("实际的自动装配 Class 名单：");
        configurations.forEach(System.out::println);

        // 排除的自动装配 Class 名单
        Set<String> exclusions = event.getExclusions();
        // 输出各自数量
        System.out.println("排除的自动装配 Class 名单：");
        exclusions.forEach(System.out::println);
    }
}
