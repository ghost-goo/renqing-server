package com.renqing.feel.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 人情表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("human_feel")
@ApiModel(value = "HumanFeel对象", description = "人情表")
public class HumanFeel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "人情类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "随礼类型")
    @TableField("category")
    private String category;

    @ApiModelProperty(value = "礼金")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "日期")
    @TableField("create_date")
    private LocalDate createDate;

    @ApiModelProperty(value = "时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private long pageNum;

    @TableField(exist = false)
    private long pageSize;


}
