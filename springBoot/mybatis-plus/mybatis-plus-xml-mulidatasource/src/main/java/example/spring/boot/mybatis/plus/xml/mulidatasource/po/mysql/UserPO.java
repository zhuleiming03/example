package example.spring.boot.mybatis.plus.xml.mulidatasource.po.mysql;

import com.baomidou.mybatisplus.annotation.*;
import example.spring.boot.mybatis.plus.xml.mulidatasource.enums.UserSexEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@TableName(value = "t_user")
public class UserPO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "card_no")
    private Long cardNo;

    private String name;

    private UserSexEnum sex;

    private Short age;

    @TableField(value = "cheap_rate")
    private Double cheapRate;

    private BigDecimal balance;

    private LocalDate birthday;

    @TableField(value = "create_time")
    private Timestamp createTime;

    @TableField(value = "is_vaild")
    private Boolean vaild;
}
