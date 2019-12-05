package example.spring.boot.json.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import example.spring.boot.json.json.serializer.BigDecimalSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class SubjectPO {

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
