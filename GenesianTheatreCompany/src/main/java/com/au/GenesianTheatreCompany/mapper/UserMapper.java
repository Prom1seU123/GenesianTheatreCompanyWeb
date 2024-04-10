package com.au.GenesianTheatreCompany.mapper;

import com.au.GenesianTheatreCompany.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<Users> {

    List<Users> listAll();

    boolean saveUser(@Param("username")String username, @Param("pwd")String pwd);

}
