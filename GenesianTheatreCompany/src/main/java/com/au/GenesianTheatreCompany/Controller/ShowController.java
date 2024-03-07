package com.au.GenesianTheatreCompany.Controller;

import com.au.GenesianTheatreCompany.entity.Show;
import com.au.GenesianTheatreCompany.service.ShowService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @GetMapping("/hello")
    public String hello() {
        return "hello show";
    }
    @Autowired
    private ShowService showService;
    @GetMapping("/list")
    public List<Show> list() {
        return showService.listAll();
    }

}
