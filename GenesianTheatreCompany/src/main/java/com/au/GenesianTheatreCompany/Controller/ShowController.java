package com.au.GenesianTheatreCompany.Controller;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.Show;
import com.au.GenesianTheatreCompany.service.ShowService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;
    @GetMapping("/list")
    public Result list() {
        return showService.listAll();
    }
    @PostMapping("/save")
    public boolean save(@RequestBody Show show) {
        return showService.save(show);
    }


    //modify
    @PostMapping("/mod")
    public boolean mod(@RequestBody Show show) {
        return showService.updateById(show);
    }
    //add or modify
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Show show) {
        return showService.saveOrUpdate(show);
    }
    //delete
    @GetMapping("/delete")
    public boolean delete(Long pid) {
        return showService.removeById(pid);
    }

    //fuzzy search
    @PostMapping("/fSearch")
    public List<Show> fSearch(@RequestBody Show show) {
        LambdaQueryWrapper<Show> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Show::getPname, show.getPname());
        return showService.list(lambdaQueryWrapper);
    }

    //precise search
    @PostMapping("/pSearch")
    public List<Show> pSearch(@RequestBody Show show) {
        LambdaQueryWrapper<Show> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Show::getPname, show.getPname());
        return showService.list(lambdaQueryWrapper);
    }

}
