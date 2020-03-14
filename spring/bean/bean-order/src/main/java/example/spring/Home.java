package example.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class Home implements  SmartLifecycle, InitializingBean,
        DisposableBean, BeanNameAware, ApplicationContextAware,HomeService, BeanFactoryAware, BeanPostProcessor {

    private boolean isRunning;

    private Integer id;

    public Home() {
        System.out.println("class Home init");
    }

    public void print() {
        System.out.println("Home Service init");
    }

    public void setBeanName(String name) {
        System.out.println("[BeanNameAware] set bean name ");
    }

    public void setBeanFactory(BeanFactory var1) throws BeansException {
        System.out.println("[BeanFactoryAware] set bean name factory");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("[ApplicationContextAware] set application context");
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor] before initialization " + beanName);
        return bean;
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("[InitializingBean] after properties set");
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor] after initialization " + beanName);
        return bean;
    }


    public Home(Integer id) {
        System.out.println("class Home(id) init");
    }


    public void start() {
        isRunning = true;
        System.out.println("LifeCycle start");
    }

    public void stop() {
        System.out.println("LifeCycle stop");
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isAutoStartup() {
        return true;
    }

    public void stop(Runnable callback) {
        System.out.println("LifeScycle stop");
        callback.run();
    }

    public int getPhase() {
        return 0;
    }


    public void destroy() throws Exception {
        System.out.println("destroy");
    }
}
