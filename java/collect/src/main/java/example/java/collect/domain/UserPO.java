package example.java.collect.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserPO {

    public UserPO() {
        this.createTime = LocalDateTime.now();
        this.balance = new BigDecimal(0);
    }

    private Integer userId;

    private String userName;

    private LocalDateTime createTime;

    private BigDecimal balance;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }
}
