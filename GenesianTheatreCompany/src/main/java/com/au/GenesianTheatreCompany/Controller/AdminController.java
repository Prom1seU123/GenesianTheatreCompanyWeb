package com.au.GenesianTheatreCompany.Controller;

import com.au.GenesianTheatreCompany.entity.Admin;
import com.au.GenesianTheatreCompany.entity.DTO.Admin1;
import com.au.GenesianTheatreCompany.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String hello() {
        return "hello srh";
    }
    @Autowired
    private AdminService adminService;
    @GetMapping("/list")
    public List<Admin> list() {
        return adminService.listAllAdmin();
    }

    //add
    @PostMapping("/save")
    public boolean save(@RequestBody Admin1 admin1) {
        return adminService.saveAdmin(admin1.getUsername(), admin1.getPwd());
    }
    @PostMapping("/save1")
    public boolean save1(@RequestBody Admin admin) {
        return adminService.save(admin);
    }


    //modify
    @PostMapping("/mod")
    public boolean mod(@RequestBody Admin admin) {
        return adminService.updateById(admin);
    }
    //add or modify
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Admin admin) {
        return adminService.saveOrUpdate(admin);
    }
    //delete
    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return adminService.removeById(id);
    }

    //fuzzy search
    @PostMapping("/fSearch")
    public List<Admin> fSearch(@RequestBody Admin admin) {
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Admin::getUsername, admin.getUsername());
        return adminService.list(lambdaQueryWrapper);
    }

    //precise search
    @PostMapping("/pSearch")
    public List<Admin> pSearch(@RequestBody Admin admin) {
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Admin::getUsername, admin.getUsername());
        return adminService.list(lambdaQueryWrapper);
    }
}
