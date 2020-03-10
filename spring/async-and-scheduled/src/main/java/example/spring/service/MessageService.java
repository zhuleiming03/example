package example.spring.service;

import org.springframework.scheduling.annotation.Async;

public interface MessageService {

    @Async
    void sendMessage();

    void showMessage();
}
