package com.au.GenesianTheatreCompany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("users")
public class Users {
    @TableId(value = "uid", type = IdType.AUTO)
    @TableField("uid")
    private Long uid;
    @TableField("username")
    private String username;
    @TableField("pwd")
    private String pwd;
    @TableField("firstname")
    private String firstname;
    @TableField("lastname")
    private String lastname;
    @TableField("email")
    private String email;
    @TableField("roles")
    private Integer roles;
}
