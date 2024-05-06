package com.au.GenesianTheatreCompany.service.impl;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Pages;
import com.au.GenesianTheatreCompany.mapper.PageMapper;
import com.au.GenesianTheatreCompany.service.PageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Pages> implements PageService {
    @Resource
    private PageMapper pageMapper;
    @Override
    public Result listAllPages() {
        return Result.suc(pageMapper.listAll());
    }

    @Override
    public Result getPageById(Long pgid) {
        return Result.suc(pageMapper.getPageById(pgid));
    };
}
