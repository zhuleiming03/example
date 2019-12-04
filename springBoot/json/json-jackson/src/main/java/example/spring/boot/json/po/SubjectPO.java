package example.spring.boot.json.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import example.spring.boot.json.domain.SerializerBigDecimal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SubjectPO {

    private Long billItemId;

    private Integer billId;

    private BigDecimal dueAmount;

    private BigDecimal receiveAmount;

    private String name;

    private String describe;

    private LocalDate billDay;

    private LocalTime deductTime;

    private Timestamp createTime;

    private LocalDateTime updateTime;

    private Boolean vaild;

    //-----------------------------------------------------------

    /**
     * JsonProperty 重命名
     */
    @JsonProperty("ID")
    public Long getBillItemId() {
        return billItemId;
    }

    public Integer getBillId() {
        return billId;
    }

    /**
     * 修改序列化规则，BigDecimal 保留两位，四舍五入
     */
    @JsonSerialize(using = SerializerBigDecimal.class)
    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    /**
     * 修改序列化规则，BigDecimal 保留两位，四舍五入
     */
    @JsonSerialize(using = SerializerBigDecimal.class)
    public BigDecimal getReceiveAmount() {
        return receiveAmount;
    }

    public String getName() {
        return name;
    }

    /**
     * 序列化忽略该字段
     */
    @JsonIgnore
    public String getDescribe() {
        return describe;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getBillDay() {
        return billDay;
    }

    public LocalTime getDeductTime() {
        return deductTime;
    }

    /**
     * 对于Date/Time/Timestamp 字段，可以指定格式化格式
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Boolean getVaild() {
        return vaild;
    }

    //-------------------------------------------------------

    public void setBillItemId(Long billItemId) {
        this.billItemId = billItemId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setBillDay(LocalDate billDay) {
        this.billDay = billDay;
    }

    public void setDeductTime(LocalTime deductTime) {
        this.deductTime = deductTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void setVaild(Boolean vaild) {
        this.vaild = vaild;
    }
}
