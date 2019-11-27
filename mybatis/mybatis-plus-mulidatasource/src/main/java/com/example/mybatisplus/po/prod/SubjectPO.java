package com.example.mybatisplus.po.prod;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "dbo.Subject")
public class SubjectPO {

    /**
     * 科目编号
     */
    @TableId(value = "SubjectId", type = IdType.NONE)
    private Integer id;

    /**
     * 科目名称
     */
    @TableField(value = "SubjectName")
    private String name;

    /**
     * 科目类型(0:默认,无类型 1:基础科目 2:逾期科目 3:提前清贷科目 4:手续费科目(保证金等) 5:诉讼科目
     */
    @TableField(value = "SubjectKind")
    private Integer kind;

    /**
     * 创建时间
     */
    @TableField(value = "Createtime")
    private Timestamp createtime;

    /**
     * 是否有效
     */
    @TableField(value = "IsValid")
    private Boolean valid;

    /**
     * 征信上报类型(0.表示无类型 1.担保类型)
     */
    @TableField(value = "CreditCheckingType")
    private Integer creditCheckingType;

    /**
     * 财务汇总报表取值计算范围
     */
    @TableField(value = "FinanceSubjectType")
    private Integer financeSubjectType;

}
