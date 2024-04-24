package com.au.GenesianTheatreCompany.service.impl;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Show;
import com.au.GenesianTheatreCompany.mapper.ShowMapper;
import com.au.GenesianTheatreCompany.service.ShowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ShowServiceImpl extends ServiceImpl<ShowMapper, Show> implements ShowService {
    @Resource
    private ShowMapper showMapper;
    @Override
    public Result listAll() {
        return Result.suc(showMapper.listAll());
    }

    @Override
    public Result findShowsByStartYear(int year) {
        return Result.suc(showMapper.findShowsByStartYear(year));
    }

    @Override
    public Result findAllDistinctYears() {
        return Result.suc(showMapper.findAllDistinctYears());
    }

    @Override
    public Result findShowDetailByPid(Long pid) {
        return Result.suc(showMapper.findShowDetailByPid(pid));
    }
    @Override
    public String getPnameByPid(Long pid) {
        return showMapper.getPnameByPid(pid);
    }
}