package com.au.GenesianTheatreCompany.Controller;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Pages;
import com.au.GenesianTheatreCompany.entity.Users;
import com.au.GenesianTheatreCompany.service.LoggingService;
import com.au.GenesianTheatreCompany.service.PageService;
import com.au.GenesianTheatreCompany.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page")
public class PageController {
    @Autowired
    private PageService pageService;
    @Autowired
    private UserService userService;
    @Autowired
    private LoggingService loggingService;

    @GetMapping("/list")
    public Result list() {
        return pageService.listAllPages();
    }

    @GetMapping("/getPageById")
    public Result getPageById(Long pgid) {
        return pageService.getPageById(pgid);
    }

    //modify
    @PostMapping("/mod")
    public Result mod(@RequestBody Pages pages, @RequestParam Long adminUid) {
        String logMessage = String.format("%s modifies the page id: %s",
                userService.getEmailByUid(adminUid),
                pages.getPgid());
        loggingService.writeLog(logMessage);
        return Result.suc(pageService.updateById(pages));
    }

}
