package com.au.GenesianTheatreCompany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("admins")
public class Admin {
    @TableId(value = "aid", type = IdType.AUTO)
    @TableField("aid")
    private Long aid;
    @TableField("username")
    private String username;
    @TableField("pwd")
    private String pwd;
}
