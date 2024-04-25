package com.au.GenesianTheatreCompany.service;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<Users> {

    Result listAllUser();

    boolean saveUser(String username, String password);
    boolean checkUidIsValid(Long uid);
    String getEmailByUid(Long uid);
}
