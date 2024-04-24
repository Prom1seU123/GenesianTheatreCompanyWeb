package com.au.GenesianTheatreCompany.mapper;

import com.au.GenesianTheatreCompany.entity.Show;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShowMapper extends BaseMapper<Show> {
    List<Show> listAll();

    List<Show> findShowsByStartYear(int year);

    List<Integer> findAllDistinctYears();

    Show findShowDetailByPid(@Param("pid")Long pid);

    String getPnameByPid(@Param("pid")Long pid);
}
