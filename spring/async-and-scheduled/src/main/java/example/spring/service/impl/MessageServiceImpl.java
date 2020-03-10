package example.spring.service.impl;

import example.spring.service.MessageService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;


@Service
public class MessageServiceImpl implements MessageService {

    @Override
    @Async
    public void sendMessage() {
        try {
            System.out.println(String.format("[info][%s]message begin send", LocalTime.now()));
            Thread.sleep(5_000L);
            System.out.println(String.format("[info][%s]message send success", LocalTime.now()));
        } catch (InterruptedException ignore) {
            System.out.println(String.format("[error][%s]send error", LocalTime.now()));
        }
    }

    /**
     * fixedDelay 计划任务间隔时间
     * initialDelay 计划任务距程序启动开始时间
     */
    @Override
    @Scheduled(fixedDelay = 10_000L, initialDelay = 60_000L)
    public void showMessage(){
        System.out.println(String.format("[info][%s]show message every 10s", LocalTime.now()));
    }
}
