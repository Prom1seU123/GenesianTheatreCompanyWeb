package com.au.GenesianTheatreCompany.service.impl;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Users;
import com.au.GenesianTheatreCompany.mapper.UserMapper;
import com.au.GenesianTheatreCompany.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public Result listAllUser() {
        return Result.suc(userMapper.listAll());
    }

    @Override
    public boolean saveUser(String username, String password) {
        return userMapper.saveUser(username, password);
    }

}
