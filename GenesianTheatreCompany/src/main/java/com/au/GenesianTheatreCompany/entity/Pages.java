package com.au.GenesianTheatreCompany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("pages")
public class Pages {
    @TableId(value = "pgid", type = IdType.INPUT)
    @TableField("pgid")
    private Long pgid;
    @TableField("title")
    private String title;
    @TableField("contents")
    private String contents;
    @TableField("image")
    private String image;
}
