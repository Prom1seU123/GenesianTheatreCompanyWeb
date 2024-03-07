package com.au.GenesianTheatreCompany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@TableName("shows")
public class Show {

    @TableId(value = "pid", type = IdType.AUTO)
    @TableField("pid")
    private Long pid;
    @TableField("pname")
    private String pname;
    @TableField("subtitle")
    private String subtitle;
    @TableField("startdate")
    private LocalDate startdate;
    @TableField("enddate")
    private LocalDate enddate;
    @TableField("productions")
    private String productions;
    @TableField("casts")
    private String casts;
    @TableField("crews")
    private String crews;
    @TableField("contents")
    private String contents;
    @TableField("cover")
    private String cover;
    @TableField("stills")
    private String stills;
    private LocalDate eventDate;
}
