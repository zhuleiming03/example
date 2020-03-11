package example.spring.domain;

import javax.validation.constraints.NotNull;

public class User {

    @NotNull(message = "user's name can't null")
    private String userName;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
