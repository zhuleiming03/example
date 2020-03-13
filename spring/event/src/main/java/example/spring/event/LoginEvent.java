package example.spring.event;

public class LoginEvent extends AuthenticationEvent {
    public LoginEvent(String username) {
        super(username);
    }
}
