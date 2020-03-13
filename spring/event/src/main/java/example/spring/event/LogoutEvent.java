package example.spring.event;

public class LogoutEvent extends AuthenticationEvent {
    public LogoutEvent(String userName) {
        super(userName);
    }
}
