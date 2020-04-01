package example.spring.boot.annotation.enable.instance;

import example.spring.boot.annotation.enable.annotation.EnableServer;
import example.spring.boot.annotation.enable.service.Server;
import org.springframework.context.annotation.Configuration;

@EnableServer(type = Server.Type.HTTP)
@Configuration
public class InstanceTwo {
}
