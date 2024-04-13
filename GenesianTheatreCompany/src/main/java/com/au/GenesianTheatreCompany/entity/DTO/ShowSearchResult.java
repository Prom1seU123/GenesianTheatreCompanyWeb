package com.au.GenesianTheatreCompany.entity.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ShowSearchResult {
    private Long pid;
    private String pname;
    private String subtitle;
    private LocalDate startdate;

    // 构造函数
    public ShowSearchResult(Long pid, String pname, String subtitle, LocalDate startdate) {
        this.pid = pid;
        this.pname = pname;
        this.subtitle = subtitle;
        this.startdate = startdate;
    }
}
