package com.example.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "config.EnumTable")
public class EnumTablePO {

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 表名
     */
    @TableField(value = "TableName")
    private String tableName;

    /**
     * 表ID
     */
    @TableField(value = "TableCode")
    private Integer tableCode;

    /**
     * 架构名
     */
    @TableField(value = "SchemasName")
    private String schemasName;

    /**
     * 架构ID
     */
    @TableField(value = "SchemasCode")
    private Integer schemasCode;

    /**
     * 是否有自增主键
     */
    @TableField(value = "HasIdentity")
    private Boolean identity;

    /**
     * 是否有效
     */
    @TableField(value = "IsVaild")
    private Boolean vaild;
}
