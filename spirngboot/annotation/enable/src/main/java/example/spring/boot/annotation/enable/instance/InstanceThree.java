package example.spring.boot.annotation.enable.instance;

import example.spring.boot.annotation.enable.annotation.EnableServerExtend;
import example.spring.boot.annotation.enable.service.Server;
import org.springframework.context.annotation.Configuration;

@EnableServerExtend(type = Server.Type.FTP)
@Configuration
public class InstanceThree {
}
