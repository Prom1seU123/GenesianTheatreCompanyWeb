package com.au.GenesianTheatreCompany.service;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Pages;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface PageService extends IService<Pages> {

    Result listAllPages();
    Result getPageById(Long pgid);
}
