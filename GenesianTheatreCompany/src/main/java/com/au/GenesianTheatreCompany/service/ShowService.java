package com.au.GenesianTheatreCompany.service;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Show;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface ShowService extends IService<Show> {
    Result listAll();

    Result findShowsByStartYear(int year);

    Result findAllDistinctYears();

    Result findShowDetailByPid(Long pid);
}
