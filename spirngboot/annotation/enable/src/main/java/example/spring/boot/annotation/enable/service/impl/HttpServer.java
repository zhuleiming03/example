package example.spring.boot.annotation.enable.service.impl;

import example.spring.boot.annotation.enable.service.Server;
import org.springframework.stereotype.Component;

@Component
public class HttpServer implements Server {

    @Override
    public void start() {
        System.out.println("HTTP 服务器启动中...");
    }

    @Override
    public void stop() {
        System.out.println("HTTP 服务器关闭中...");
    }
}
