package com.au.GenesianTheatreCompany.mapper;

import com.au.GenesianTheatreCompany.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    List<Admin> listAll();

    boolean saveAdmin(@Param("username")String username, @Param("pwd")String pwd);

}
