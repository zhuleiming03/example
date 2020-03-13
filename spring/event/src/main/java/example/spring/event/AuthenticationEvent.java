package example.spring.event;

import org.springframework.context.ApplicationEvent;

public class AuthenticationEvent extends ApplicationEvent {
    public AuthenticationEvent(Object source) {
        super(source);
    }
}
