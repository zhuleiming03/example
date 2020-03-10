package example.spring.domain;

public class User {

    private long userId;
    private String userNo;
    private String name;

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public String getUserNo()
    {
        return userNo;
    }

    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
