package com.au.GenesianTheatreCompany.mapper;

import com.au.GenesianTheatreCompany.entity.Pages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PageMapper extends BaseMapper<Pages> {

    List<Pages> listAll();

    Pages getPageById(@Param("pgid")Long uid);
}
