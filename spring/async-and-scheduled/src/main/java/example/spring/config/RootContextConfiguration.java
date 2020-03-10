package example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Controller;

import java.util.concurrent.Executor;

@ComponentScan(basePackages = "example.spring",
        excludeFilters = @ComponentScan.Filter(Controller.class))
@EnableAsync(proxyTargetClass = true)
@Configuration
@EnableScheduling
public class RootContextConfiguration
        implements AsyncConfigurer, SchedulingConfigurer {


    @Override
    public Executor getAsyncExecutor() {
        Executor executor = this.taskScheduler();
        System.out.println("Configuring asynchronous method executor : " + executor);
        return executor;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        TaskScheduler scheduler = this.taskScheduler();
        System.out.println("Configuring scheduled method executor :" + scheduler);
        registrar.setTaskScheduler(scheduler);
    }


    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {

        System.out.println("Setting up thread pool task scheduler with 20 threads.");
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.setThreadNamePrefix("task-");
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }
}
