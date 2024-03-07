package com.au.GenesianTheatreCompany.service.impl;

import com.au.GenesianTheatreCompany.entity.Admin;
import com.au.GenesianTheatreCompany.mapper.AdminMapper;
import com.au.GenesianTheatreCompany.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public List<Admin> listAllAdmin() {
        return adminMapper.listAll();
    }

    @Override
    public boolean saveAdmin(String username, String password) {
        return adminMapper.saveAdmin(username, password);
    }

}
