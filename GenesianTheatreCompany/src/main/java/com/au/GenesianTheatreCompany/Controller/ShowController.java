package com.au.GenesianTheatreCompany.Controller;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.DTO.ShowSearchResult;
import com.au.GenesianTheatreCompany.entity.Show;
import com.au.GenesianTheatreCompany.service.ShowService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/fSearch")
    public Result fSearch(String kw) {
        LambdaQueryWrapper<Show> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.apply("LOWER(pname) LIKE LOWER({0})", "%" + kw + "%")
                .or()
                .apply("LOWER(subtitle) LIKE LOWER({0})", "%" + kw + "%")
                .or()
                .apply("LOWER(productions) LIKE LOWER({0})", "%" + kw + "%")
                .or()
                .apply("LOWER(casts) LIKE LOWER({0})", "%" + kw + "%")
                .or()
                .apply("LOWER(crews) LIKE LOWER({0})", "%" + kw + "%");
        List<Show> shows = showService.list(lambdaQueryWrapper);
        List<ShowSearchResult> showDTOs = shows.stream()
                .map(show -> new ShowSearchResult(show.getPid(), show.getPname(), show.getSubtitle(), show.getStartdate()))
                .collect(Collectors.toList());
        return Result.suc(showDTOs);
    }


    //precise search
    @PostMapping("/pSearch")
    public List<Show> pSearch(@RequestBody Show show) {
        LambdaQueryWrapper<Show> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Show::getPname, show.getPname());
        return showService.list(lambdaQueryWrapper);
    }

    @GetMapping("/previousShow")
    public Result findAllDistinctYears() {
        return showService.findAllDistinctYears();
    }

    @GetMapping("/previousShow/{year}")
    public Result findShowsByStartYear(@PathVariable("year") int year) {
        return showService.findShowsByStartYear(year);
    }

    @GetMapping("/detail/{pid}")
    public Result findShowDetailByPid(@PathVariable("pid") Long pid) {
        return showService.findShowDetailByPid(pid);
    }


}
