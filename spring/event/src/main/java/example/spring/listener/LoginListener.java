package example.spring.listener;

import example.spring.event.LoginEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class LoginListener implements ApplicationListener<LoginEvent> {

    @Override
    public void onApplicationEvent(LoginEvent event) {
        System.out.println("login event , user : " + event.getSource());
    }
}
