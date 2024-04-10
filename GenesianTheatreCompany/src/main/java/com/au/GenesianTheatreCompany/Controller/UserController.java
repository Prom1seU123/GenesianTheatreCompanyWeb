package com.au.GenesianTheatreCompany.Controller;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Users;
import com.au.GenesianTheatreCompany.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public Result list() {
        return userService.listAllUser();
    }

    //add
//    @PostMapping("/save")
//    public boolean save(@RequestBody Admin1 admin1) {
//        return adminService.saveAdmin(admin1.getUsername(), admin1.getPwd());
//    }
    @PostMapping("/save1")
    public boolean save1(@RequestBody Users users) {
        return userService.save(users);
    }


    //modify
    @PostMapping("/mod")
    public boolean mod(@RequestBody Users users) {
        return userService.updateById(users);
    }
    //add or modify
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Users users) {
        return userService.saveOrUpdate(users);
    }
    //delete
    @GetMapping("/delete")
    public boolean delete(Long aid) {
        return userService.removeById(aid);
    }

    //fuzzy search
    @PostMapping("/fSearch")
    public List<Users> fSearch(@RequestBody Users users) {
        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.apply("LOWER(username) LIKE LOWER({0})", "%" + users.getUsername() + "%");
        return userService.list(lambdaQueryWrapper);
    }

    //precise search
    @PostMapping("/pSearch")
    public List<Users> pSearch(@RequestBody Users users) {
        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Users::getUsername, users.getUsername());
        return userService.list(lambdaQueryWrapper);
    }
}
