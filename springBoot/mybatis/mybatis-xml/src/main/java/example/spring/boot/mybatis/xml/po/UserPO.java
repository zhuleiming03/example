package example.spring.boot.mybatis.xml.po;

import example.spring.boot.mybatis.xml.enums.UserSexEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class UserPO {
    private Long id;
    private Long cardNo;
    private String name;
    private UserSexEnum sex;
    private Short age;
    private Double cheapRate;
    private BigDecimal balance;
    private LocalDate birthday;
    private Timestamp createTime;
    private Boolean vaild;
}
