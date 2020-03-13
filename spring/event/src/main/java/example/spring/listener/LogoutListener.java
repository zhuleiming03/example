package example.spring.listener;

import example.spring.event.LogoutEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class LogoutListener implements ApplicationListener<LogoutEvent> {

    @Override
    public void onApplicationEvent(LogoutEvent event) {
        System.out.println("logout event , user : " + event.getSource());
    }
}
