package example.spring.boot.json.po;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import example.spring.boot.json.json.serializer.BigDecimalSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
//反序列化时会忽略所有没有Getter和Setter的属性
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubjectPO {

    public SubjectPO(){}

    public SubjectPO(Integer billId,Double dueAmount,String name) {
        this.billItemId = System.currentTimeMillis();
        this.billId = billId;
        this.dueAmount = new BigDecimal(dueAmount);
        this.receiveAmount = new BigDecimal(0);
        this.name = name;
        this.describe = name;
        this.billDay = LocalDate.now();
        this.deductTime = LocalTime.now();
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.updateTime = LocalDateTime.now();
        this.vaild = true;
    }

    /**
     * JsonProperty 重命名
     */
    @JsonProperty("ID")
    private Long billItemId;

    private Integer billId;

    /**
     * 序列化重写规则，BigDecimal 保留两位，四舍五入
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal dueAmount;

    private BigDecimal receiveAmount;

    private String name;

    /**
     * 序列化忽略该字段
     */
    @JsonIgnore
    private String describe;

    private LocalDate billDay;

    private LocalTime deductTime;

    /**
     * 对于Date/Time/Timestamp 字段，可以指定格式化格式
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    private LocalDateTime updateTime;

    private Boolean vaild;

}
