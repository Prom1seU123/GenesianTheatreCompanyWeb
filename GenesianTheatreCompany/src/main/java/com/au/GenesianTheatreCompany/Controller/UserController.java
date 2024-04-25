package com.au.GenesianTheatreCompany.Controller;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Users;
import com.au.GenesianTheatreCompany.service.LoggingService;
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
    @Autowired
    private LoggingService loggingService;
    @GetMapping("/list")
    public Result list() {
        return userService.listAllUser();
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Users users, @RequestParam Long uid) {
        String logMessage = String.format("%s adds the user: %s",
                userService.getEmailByUid(uid),
                users.getEmail());
        loggingService.writeLog(logMessage);
        return userService.save(users);
    }


    //modify
    @PostMapping("/mod")
    public boolean mod(@RequestBody Users users, @RequestParam Long uid) {
        String logMessage = String.format("%s modifies the user: %s",
                userService.getEmailByUid(uid),
                users.getEmail());
        loggingService.writeLog(logMessage);
        return userService.updateById(users);
    }
    //add or modify
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Users users, @RequestParam Long uid) {
        String logMessage = String.format("%s saves or modifies the user: %s",
                userService.getEmailByUid(uid),
                users.getEmail());
        loggingService.writeLog(logMessage);
        return userService.saveOrUpdate(users);
    }
    //delete
    @GetMapping("/delete")
    public boolean delete(Long uid, @RequestParam Long adminUid) {
        String logMessage = String.format("%s deletes the user: %s",
                userService.getEmailByUid(adminUid),
                userService.getEmailByUid(uid));
        loggingService.writeLog(logMessage);
        return userService.removeById(uid);
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
    @PostMapping("/login")
    public Result login(@RequestBody Users users) {

        List list = userService.lambdaQuery()
                .eq(Users::getEmail, users.getEmail())
                .eq(Users::getPwd, users.getPwd()).list();
        String logMessage = String.format("%s logs in", users.getEmail());
        loggingService.writeLog(logMessage);
        return list.size() > 0 ? Result.suc(list.get(0)):Result.fail();
    }
}
