package com.au.GenesianTheatreCompany.mapper;

import com.au.GenesianTheatreCompany.entity.Show;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShowMapper extends BaseMapper<Show> {
    List<Show> listAll();
}
