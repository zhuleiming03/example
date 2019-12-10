package example.spring.boot.mybatis.plus.xml.mulidatasource.po.mssql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import example.spring.boot.mybatis.plus.xml.mulidatasource.enums.UserSexEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@TableName(value = "dbo.UserInfo")
public class UserPO {

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "CardNo")
    private Long cardNo;

    @TableField(value = "Name")
    private String name;

    @TableField(value = "Sex")
    private UserSexEnum sex;

    @TableField(value = "Age")
    private Short age;

    @TableField(value = "CheapRate")
    private Double cheapRate;

    @TableField(value = "Balance")
    private BigDecimal balance;

    @TableField(value = "Birthday")
    private LocalDate birthday;

    @TableField(value = "CreateTime")
    private Timestamp createTime;

    @TableField(value = "IsVaild")
    private Boolean vaild;

}
