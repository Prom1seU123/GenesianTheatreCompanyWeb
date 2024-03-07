package com.au.GenesianTheatreCompany.service;

import com.au.GenesianTheatreCompany.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService extends IService<Admin> {

    List<Admin> listAllAdmin();

    boolean saveAdmin(String username, String password);
}
