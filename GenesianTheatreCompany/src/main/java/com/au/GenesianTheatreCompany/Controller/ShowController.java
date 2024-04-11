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
    public Result fSearch(@RequestParam(required = false) String kw, @RequestParam(required = false) Integer year) {
        LambdaQueryWrapper<Show> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        boolean hasKeyword = kw != null && !kw.trim().isEmpty();
        boolean hasYear = year != null;

        // Begin constructing the query only if at least one condition is present
        if (hasKeyword || hasYear) {
            lambdaQueryWrapper.and(wrapper -> {
                boolean addedCondition = false; // Track if any condition has been added

                // Add keyword conditions
                if (hasKeyword) {
                    String keywordCondition = "%" + kw.trim() + "%";
                    wrapper.apply("LOWER(pname) LIKE LOWER({0})", keywordCondition)
                            .or()
                            .apply("LOWER(subtitle) LIKE LOWER({0})", keywordCondition)
                            .or()
                            .apply("LOWER(productions) LIKE LOWER({0})", keywordCondition)
                            .or()
                            .apply("LOWER(casts) LIKE LOWER({0})", keywordCondition)
                            .or()
                            .apply("LOWER(crews) LIKE LOWER({0})", keywordCondition);
                    addedCondition = true;
                }

                // Add year condition
                if (hasYear) {
                    String yearCondition = "EXTRACT(YEAR FROM startdate) = " + year;
                    if (addedCondition) {
                        // If any keyword condition has been added, use AND to combine with the year condition
                        wrapper.and(i -> i.apply(yearCondition));
                    } else {
                        // If no keyword condition has been added, directly apply the year condition
                        wrapper.apply(yearCondition);
                    }
                }
            });
        }

        // Execute the query and process the results
        List<Show> shows = showService.list(lambdaQueryWrapper);
        List<ShowSearchResult> showSearchResult = shows.stream()
                .map(show -> new ShowSearchResult(show.getPid(), show.getPname(), show.getSubtitle(), show.getStartdate()))
                .collect(Collectors.toList());
        return Result.suc(showSearchResult);
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
