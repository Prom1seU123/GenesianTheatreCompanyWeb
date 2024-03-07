package com.au.GenesianTheatreCompany.service;

import com.au.GenesianTheatreCompany.entity.Show;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowService extends IService<Show> {
    List<Show> listAll();
}
